package kz.halyk.finservice.test.MarketPlace.dto.paymentDetails;


import kz.halyk.finservice.test.MarketPlace.entity.OrderDetails;
import kz.halyk.finservice.test.MarketPlace.entity.PaymentStatus;
import kz.halyk.finservice.test.MarketPlace.entity.UserPayment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDetailsDto {
    private PaymentStatus paymentStatus;
}
