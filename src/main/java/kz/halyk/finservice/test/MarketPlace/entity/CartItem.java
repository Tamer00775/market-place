package kz.halyk.finservice.test.MarketPlace.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "cart_item")
public class CartItem extends AbstractEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(name = "quantity", nullable = false)
    @Getter
    @Setter
    private Integer quantity;

    @Column(name = "total_price", nullable = false)
    @Getter
    @Setter
    private Integer totalPrice;

    @Column(name = "deleted", nullable = false, columnDefinition = "boolean default false")
    @Getter
    @Setter
    private Boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Getter
    @Setter
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @Getter
    @Setter
    private Product product;

}
