package kz.halyk.finservice.test.MarketPlace.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "product")
@ToString
public class Product extends AbstractEntity {
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
    @Getter
    @Setter
    private Integer price;

    @OneToOne
    @JoinColumn(name = "category_id")
    @Getter
    @Setter
    private Category category;

    @ManyToOne
    @JoinColumn(name = "inventory_id", nullable = false)
    @Getter
    @Setter
    private Inventory inventory;

}
