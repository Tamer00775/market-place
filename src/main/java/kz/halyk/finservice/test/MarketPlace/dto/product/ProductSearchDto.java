package kz.halyk.finservice.test.MarketPlace.dto.product;

import kz.halyk.finservice.test.MarketPlace.enums.CategoryCode;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProductSearchDto {
    private String productNameQuery;
    private Integer priceFrom;
    private Integer priceTo;
    private CategoryCode categoryCode;
}
