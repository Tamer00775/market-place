package kz.halyk.finservice.test.MarketPlace.dto.userPayment;

import kz.halyk.finservice.test.MarketPlace.entity.User;
import kz.halyk.finservice.test.MarketPlace.enums.PaymentType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserPaymentDto {
    private Long id;
    private PaymentType paymentType;
    private String provider;
    private String accountNo;
    private LocalDate expiry;
    private Boolean isDeleted;
    private User user;
}
