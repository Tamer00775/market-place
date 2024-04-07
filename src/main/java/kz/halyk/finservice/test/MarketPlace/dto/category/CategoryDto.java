package kz.halyk.finservice.test.MarketPlace.dto.category;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ApiModel("DTO for category of product")
public class CategoryDto {
    @ApiModelProperty("Id of product")
    private Long id;
    @NotNull(message = "The category code of the product must be not null.")
    @ApiModelProperty("Category code")
    @Size(max = 50, message = "Maximum characters in code must be less than 50")
    private String categoryCode;
    @ApiModelProperty("Description of category")
    private String description;
}
