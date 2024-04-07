package kz.halyk.finservice.test.MarketPlace.converter.product;

import kz.halyk.finservice.test.MarketPlace.dto.product.ProductCreateDto;
import kz.halyk.finservice.test.MarketPlace.entity.Product;
import kz.halyk.finservice.test.MarketPlace.service.impl.CategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductCreateConverter implements Converter<ProductCreateDto, Product> {
    private final CategoryServiceImpl categoryServiceImpl;

    @Override
    public Product convert(ProductCreateDto source) {
        Product product = new Product();
        product.setProductName(source.getProductName());
        product.setPrice(source.getPrice());
        product.setDescription(source.getDescription());
        product.setCategory(categoryServiceImpl.findCategoryByCode(source.getCategoryCode()));
        return product;
    }
}
