package kz.halyk.finservice.test.MarketPlace.entity;

import kz.halyk.finservice.test.MarketPlace.enums.PaymentType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "user_payment")
public class UserPayment extends AbstractEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type", nullable = false, length = 100)
    @Getter
    @Setter
    private PaymentType paymentType;

    @Column(name = "provider", nullable = false)
    @Getter
    @Setter
    private String provider;

    @Column(name = "account_no", nullable = false)
    @Getter
    @Setter
    private String accountNo;

    @Column(name = "expiry", nullable = false)
    @Getter
    @Setter
    private LocalDate expiry;

    @Column(name = "deleted", nullable = false, columnDefinition = "boolean default false")
    @Getter
    @Setter
    private Boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Getter
    @Setter
    private User user;

}