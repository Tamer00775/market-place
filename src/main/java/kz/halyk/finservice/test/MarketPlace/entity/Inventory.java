package kz.halyk.finservice.test.MarketPlace.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "inventory")
public class Inventory extends AbstractEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(name = "quantity", nullable = false)
    @Getter
    @Setter
    private Integer quantity;

    @Column(name = "description",columnDefinition = "text")
    @Getter
    @Setter
    private String description;

    @Column(name = "deleted", nullable = false, columnDefinition = "boolean default false")
    @Getter
    @Setter
    private Boolean isDeleted = false;
}