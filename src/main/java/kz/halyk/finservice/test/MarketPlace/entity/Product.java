package kz.halyk.finservice.test.MarketPlace.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(name = "product_name", nullable = false, length = 100)
    @Getter
    @Setter
    private String productName;

    @Column(name = "description", nullable = false)
    @Getter
    @Setter
    private String description;

    @Column(name = "deleted", nullable = false, columnDefinition = "boolean default false")
    @Getter
    @Setter
    private Boolean isDeleted = false;

    @Column(name = "price", nullable = false)
    private Integer price;

    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
