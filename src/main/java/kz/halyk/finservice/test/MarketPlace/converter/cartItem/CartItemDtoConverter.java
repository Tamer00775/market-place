package kz.halyk.finservice.test.MarketPlace.converter.cartItem;

import kz.halyk.finservice.test.MarketPlace.dto.cartItem.CartItemDto;
import kz.halyk.finservice.test.MarketPlace.dto.category.CategoryDto;
import kz.halyk.finservice.test.MarketPlace.dto.paymentDetails.PaymentDetailsDto;
import kz.halyk.finservice.test.MarketPlace.entity.CartItem;
import kz.halyk.finservice.test.MarketPlace.entity.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CartItemDtoConverter implements Converter<CartItem, CartItemDto> {
    @Override
    public CartItemDto convert(CartItem source) {
        CartItemDto dto = new CartItemDto();
        dto.setId(source.getId());
        dto.setQuantity(source.getQuantity());
        dto.setTotalPrice(source.getTotalPrice());
        dto.setIsDeleted(source.getIsDeleted());
        dto.setUser(source.getUser());
        dto.setProduct(source.getProduct());
        return dto;
    }
}
