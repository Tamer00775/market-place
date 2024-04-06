package kz.halyk.finservice.test.MarketPlace.dto.category;

import kz.halyk.finservice.test.MarketPlace.enums.CategoryCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CategoryDto {
    private Long id;
    @NotNull(message = "The category code of the product must be not null.")
    private CategoryCode categoryCode;
    private String description;
}
