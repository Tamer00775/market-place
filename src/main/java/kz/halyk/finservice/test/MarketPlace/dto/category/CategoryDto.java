package kz.halyk.finservice.test.MarketPlace.dto.category;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import kz.halyk.finservice.test.MarketPlace.enums.CategoryCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ApiModel("DTO for category of product")
public class CategoryDto {
    @ApiModelProperty("Id of product")
    private Long id;
    @NotNull(message = "The category code of the product must be not null.")
    @ApiModelProperty("Category code")
    private CategoryCode categoryCode;
    @ApiModelProperty("Description of category")
    private String description;
}
