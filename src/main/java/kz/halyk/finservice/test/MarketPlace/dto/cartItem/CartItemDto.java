package kz.halyk.finservice.test.MarketPlace.dto.cartItem;

import kz.halyk.finservice.test.MarketPlace.dto.product.ProductDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDto {
    private Long id;
    private Integer quantity;
    private Integer totalPrice;
    private Boolean isDeleted;
    private ProductDto product;
}
