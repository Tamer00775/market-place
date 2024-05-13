package kz.halyk.finservice.test.MarketPlace.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "payment_details")
public class PaymentDetails extends AbstractEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(name = "deleted", nullable = false, columnDefinition = "boolean default false")
    @Getter
    @Setter
    private Boolean isDeleted = false;


    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    @Getter
    @Setter
    private OrderDetails orderDetails;

    @ManyToOne
    @JoinColumn(name = "payment_id", nullable = false)
    @Getter
    @Setter
    private UserPayment userPayment;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    @Getter
    @Setter
    private PaymentStatus paymentStatus;



}