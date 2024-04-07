package kz.halyk.finservice.test.MarketPlace.util.validator;

import kz.halyk.finservice.test.MarketPlace.dto.category.CategoryDto;
import kz.halyk.finservice.test.MarketPlace.dto.product.ProductCreateDto;
import kz.halyk.finservice.test.MarketPlace.dto.product.ProductSearchDto;
import kz.halyk.finservice.test.MarketPlace.entity.Product;
import kz.halyk.finservice.test.MarketPlace.exception.MarketPlaceException;
import kz.halyk.finservice.test.MarketPlace.service.CategoryService;
import kz.halyk.finservice.test.MarketPlace.util.validator.product_create.ProductCreateValidator;
import kz.halyk.finservice.test.MarketPlace.util.validator.product_search.ProductSearchValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryCodeValidator implements ProductSearchValidator, ProductCreateValidator {
    private final CategoryService categoryService;

    @Override
    public void validate(ProductCreateDto productCreateDto) {
        validate(productCreateDto.getCategoryCode());
    }

    @Override
    public void validate(ProductSearchDto productSearchDto) {
        validate(productSearchDto.getCategoryCode());
    }


    public void validate(String code) throws MarketPlaceException {
        categoryService.findByCode(code);
    }
}
