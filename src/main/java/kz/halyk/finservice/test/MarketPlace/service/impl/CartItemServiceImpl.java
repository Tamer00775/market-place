package kz.halyk.finservice.test.MarketPlace.service.impl;


import kz.halyk.finservice.test.MarketPlace.converter.cartItem.CartItemDtoConverter;
import kz.halyk.finservice.test.MarketPlace.dto.cartItem.CartItemDto;
import kz.halyk.finservice.test.MarketPlace.dto.cartItem.CartItemRequestDto;
import kz.halyk.finservice.test.MarketPlace.entity.CartItem;
import kz.halyk.finservice.test.MarketPlace.entity.Inventory;
import kz.halyk.finservice.test.MarketPlace.entity.Product;
import kz.halyk.finservice.test.MarketPlace.entity.User;
import kz.halyk.finservice.test.MarketPlace.exception.MarketPlaceException;
import kz.halyk.finservice.test.MarketPlace.repository.CartItemRepository;
import kz.halyk.finservice.test.MarketPlace.repository.InventoryRepository;
import kz.halyk.finservice.test.MarketPlace.repository.ProductRepository;
import kz.halyk.finservice.test.MarketPlace.service.CartItemService;
import kz.halyk.finservice.test.MarketPlace.utils.SecurityUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static kz.halyk.finservice.test.MarketPlace.util.validator.ProductExchangeValidator.isCartItemHasEnoughQuantity;
import static kz.halyk.finservice.test.MarketPlace.util.validator.ProductExchangeValidator.isProductQuantityEnough;

@Service
@AllArgsConstructor
@Slf4j
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;

    private  CartItemDtoConverter cartItemDtoConverter;

    @Override
    @Transactional(readOnly = true)
    public CartItem findById(Long id) {
        return cartItemRepository.findById(id)
                .orElseThrow(() -> new MarketPlaceException(String.format("CartItem with id %d not found", id),
                                LocalDateTime.now(),
                                HttpStatus.BAD_REQUEST)
                );
    }

    @Override
    @Transactional(readOnly = true)
    public List<CartItemDto> findAll() {
        return cartItemRepository
                .findAll()
                .stream()
                .map(cartItemDtoConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CartItemDto> findAllByUser(User user) {
        return cartItemRepository
                .findAllByUser(user)
                .stream()
                .map(cartItemDtoConverter::convert)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CartItemDto> findAllByUser() {
        User user = Objects.requireNonNull(SecurityUtils.getCurrentPerson());
        return cartItemRepository
                .findAllByUser(user)
                .stream()
                .map(cartItemDtoConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CartItemDto> findAll(Pageable pageable) {
        return cartItemRepository.findAll(pageable).map(cartItemDtoConverter::convert);
    }

    @Override
    public void deleteById(Long id) {
        cartItemRepository.deleteById(id);
    }

    @Override
    public CartItem create(CartItemDto dto) {
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(dto.getQuantity());
        cartItem.setTotalPrice(dto.getTotalPrice());
        cartItem.setIsDeleted(dto.getIsDeleted());
        cartItem.setProduct(dto.getProduct());
        return cartItemRepository.save(cartItem);
    }

    public CartItemDto updateItem(CartItemRequestDto cartItemRequestDto) {
        User user = Objects.requireNonNull(SecurityUtils.getCurrentPerson());
        Long productId = cartItemRequestDto.getProductId();
        Integer quantity = cartItemRequestDto.getQuantity();
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new MarketPlaceException(String.format("Product with id %d not found", productId),
                        LocalDateTime.now(),
                        HttpStatus.NOT_FOUND)
                );
        if (!isProductQuantityEnough(product, quantity)) {
            throw new MarketPlaceException(
                    String.format("Product with id %d has insufficient quantity", productId),
                    LocalDateTime.now(),
                    HttpStatus.BAD_REQUEST
            );
        }
        CartItem cartItem = cartItemRepository.findAllByUser(user)
                .stream()
                .filter(cartItem1 -> cartItem1.getProduct().getId().equals(productId))
                .findAny()
                .orElseGet(() -> generateDefaultCartItem(user,product));
        cartItem = updateCartItem(cartItem,product,quantity);

        if (!isCartItemHasEnoughQuantity(cartItem)) {
            throw new MarketPlaceException(
                    String.format("Cart item with product ID %d cannot have a quantity less than zero", cartItem.getProduct().getId()),
                    LocalDateTime.now(),
                    HttpStatus.BAD_REQUEST
            );
        }
        Inventory inventory = product.getInventory();
        inventory.setQuantity(inventory.getQuantity() - quantity);
        inventoryRepository.save(inventory);

        return cartItemDtoConverter.convert(cartItemRepository.save(cartItem));
    }

    private CartItem updateCartItem(CartItem cartItem, Product product, Integer quantity) {
        cartItem.setQuantity(cartItem.getQuantity() + quantity);
        cartItem.setTotalPrice(cartItem.getTotalPrice() + product.getPrice() * quantity);
        return cartItem;
    }

    public CartItem generateDefaultCartItem(User user, Product product) {
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(0);
        cartItem.setTotalPrice(0);
        cartItem.setUser(user);
        cartItem.setProduct(product);
        return cartItem;
    }
}
