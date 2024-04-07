package kz.halyk.finservice.test.MarketPlace.dto.product;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import kz.halyk.finservice.test.MarketPlace.dto.category.CategoryDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("DTO for manipulation with product")
public class ProductDto {
    @ApiModelProperty("Name of product")
    private String productName;
    @ApiModelProperty("Price of product")
    private Integer price;
    @ApiModelProperty("Description of product")
    private String description;
    @ApiModelProperty("Category of product")
    private CategoryDto categoryDto;
}
