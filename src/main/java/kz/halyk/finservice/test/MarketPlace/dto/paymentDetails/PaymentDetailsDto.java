package kz.halyk.finservice.test.MarketPlace.dto.paymentDetails;


import kz.halyk.finservice.test.MarketPlace.entity.OrderDetails;
import kz.halyk.finservice.test.MarketPlace.entity.PaymentStatus;
import kz.halyk.finservice.test.MarketPlace.entity.UserPayment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDetailsDto {
    private Long id;
    private String productName;
    private String description;
    private Integer quantity;
    private Integer totalPrice;
    private Boolean isDeleted;
    private PaymentStatus paymentStatus;
    private UserPayment userPayment;
    private OrderDetails orderDetails;
}
