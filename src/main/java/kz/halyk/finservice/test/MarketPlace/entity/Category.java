package kz.halyk.finservice.test.MarketPlace.entity;

import kz.halyk.finservice.test.MarketPlace.enums.CategoryCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "category")
@ToString
public class Category extends AbstractEntity{
    @Id
    @Getter
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_code", nullable = false, length = 50)
    @Enumerated(value = EnumType.STRING)
    @Getter
    @Setter
    private CategoryCode categoryCode;

    @Column(name = "description")
    @Getter
    @Setter
    private String description;

    @Column(name = "deleted", columnDefinition = "boolean default false")
    private Boolean isDeleted = false;

}
