package kz.halyk.finservice.test.MarketPlace.util.validator.product_search;

import kz.halyk.finservice.test.MarketPlace.dto.product.ProductSearchDto;
import kz.halyk.finservice.test.MarketPlace.exception.MarketPlaceException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ProductPriceValidator implements ProductSearchValidator {

    @Override
    public void validate(ProductSearchDto productSearchDto) {
        if (productSearchDto.getPriceFrom() != null && productSearchDto.getPriceFrom() < 1) {
                throw new MarketPlaceException("Price From must be greater than 0",
                        LocalDateTime.now(), HttpStatus.BAD_REQUEST);
        }
        if (productSearchDto.getPriceTo() != null && productSearchDto.getPriceTo() < 1) {
            throw new MarketPlaceException("Price To must be greater than 0",
                    LocalDateTime.now(), HttpStatus.BAD_REQUEST);
        }
        if (productSearchDto.getPriceFrom() != null && productSearchDto.getPriceTo() != null) {
            if (productSearchDto.getPriceTo() < productSearchDto.getPriceFrom()) {
                throw new MarketPlaceException("Price To must be greater than Price From",
                        LocalDateTime.now(), HttpStatus.BAD_REQUEST);
            }
        }
    }
}
