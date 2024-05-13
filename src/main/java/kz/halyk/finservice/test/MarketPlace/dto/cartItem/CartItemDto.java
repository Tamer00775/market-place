package kz.halyk.finservice.test.MarketPlace.dto.cartItem;

import kz.halyk.finservice.test.MarketPlace.entity.Product;
import kz.halyk.finservice.test.MarketPlace.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDto {
    private Long id;
    private Integer quantity;
    private Integer totalPrice;
    private Boolean isDeleted;
    private Product product;
}
