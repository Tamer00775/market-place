package kz.halyk.finservice.test.MarketPlace.dto.product;

import kz.halyk.finservice.test.MarketPlace.dto.category.CategoryDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private String productName;
    private Integer price;
    private String description;
    private CategoryDto categoryDto;
}
