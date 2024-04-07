package kz.halyk.finservice.test.MarketPlace.dto.product;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import kz.halyk.finservice.test.MarketPlace.enums.CategoryCode;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@ApiModel("DTO for search product")
public class ProductSearchDto {
    @ApiModelProperty("Product name query for product")
    private String productNameQuery;
    @ApiModelProperty("Price from for product")
    private Integer priceFrom;
    @ApiModelProperty("Price from to product")
    private Integer priceTo;
    @ApiModelProperty("Category code to product")
    private CategoryCode categoryCode;
}
