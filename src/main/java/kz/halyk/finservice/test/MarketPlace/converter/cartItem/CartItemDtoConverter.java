package kz.halyk.finservice.test.MarketPlace.converter.cartItem;

import kz.halyk.finservice.test.MarketPlace.converter.product.ProductDtoConverter;
import kz.halyk.finservice.test.MarketPlace.dto.cartItem.CartItemDto;
import kz.halyk.finservice.test.MarketPlace.dto.category.CategoryDto;
import kz.halyk.finservice.test.MarketPlace.dto.paymentDetails.PaymentDetailsDto;
import kz.halyk.finservice.test.MarketPlace.entity.CartItem;
import kz.halyk.finservice.test.MarketPlace.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CartItemDtoConverter implements Converter<CartItem, CartItemDto> {
    private final ProductDtoConverter productDtoConverter;
    @Override
    public CartItemDto convert(CartItem source) {
        CartItemDto dto = new CartItemDto();
        dto.setId(source.getId());
        dto.setQuantity(source.getQuantity());
        dto.setTotalPrice(source.getTotalPrice());
        dto.setIsDeleted(source.getIsDeleted());
        dto.setProduct(productDtoConverter.convert(source.getProduct()));
        return dto;
    }
}
