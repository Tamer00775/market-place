package kz.halyk.finservice.test.MarketPlace.service.impl;


import kz.halyk.finservice.test.MarketPlace.converter.cartItem.CartItemDtoConverter;
import kz.halyk.finservice.test.MarketPlace.dto.cartItem.CartItemDto;
import kz.halyk.finservice.test.MarketPlace.entity.CartItem;
import kz.halyk.finservice.test.MarketPlace.entity.User;
import kz.halyk.finservice.test.MarketPlace.exception.MarketPlaceException;
import kz.halyk.finservice.test.MarketPlace.repository.CartItemRepository;
import kz.halyk.finservice.test.MarketPlace.service.CartItemService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;

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
        cartItem.setUser(dto.getUser());
        cartItem.setProduct(dto.getProduct());
        return cartItem;
    }
}
