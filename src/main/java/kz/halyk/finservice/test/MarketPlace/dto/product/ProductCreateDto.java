package kz.halyk.finservice.test.MarketPlace.dto.product;

import kz.halyk.finservice.test.MarketPlace.enums.CategoryCode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@Getter
@Setter
@ToString
public class ProductCreateDto {
    @NotNull(message = "The product name must be not null.")
    @NotBlank(message = "The product name must be not blank.")
    @Size(max = 50, message = "The product name must be up to 50 characters.")
    private String productName;
    @NotNull(message = "The description of the product must be not null.")
    @NotBlank(message = "The description of the product must be not blank.")
    private String description;

    @NotNull(message = "The price of the product must be not null.")
    @Min(value = 10, message = "The minimum price of the product must be greater than or equal to 10.")
    private Integer price;
    @NotNull(message = "The category code of the product must be not null.")
    private CategoryCode categoryCode;
}
