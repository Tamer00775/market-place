package kz.halyk.finservice.test.MarketPlace.dto.orderDetails;

import kz.halyk.finservice.test.MarketPlace.dto.paymentDetails.PaymentDetailsDto;
import kz.halyk.finservice.test.MarketPlace.dto.product.ProductDto;
import kz.halyk.finservice.test.MarketPlace.entity.Product;
import kz.halyk.finservice.test.MarketPlace.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailsDto {
    private Long id;
    private Boolean isDeleted;
    private Integer quantity;
    private Integer totalPrice;
    private User user;
    private ProductDto product;
    private PaymentDetailsDto paymentDetailsDto;
}
