package kz.halyk.finservice.test.MarketPlace.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import kz.halyk.finservice.test.MarketPlace.dto.product.ProductDto;
import kz.halyk.finservice.test.MarketPlace.entity.Product;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductDtoConverter implements Converter<Product, ProductDto> {
    private final CategoryDtoConverter categoryDtoConverter;

    @Override
    public ProductDto convert(Product source) {
        ProductDto productDto = new ProductDto();
        productDto.setProductName(source.getProductName());
        productDto.setCategoryDto(categoryDtoConverter.convert(source.getCategory()));
        productDto.setDescription(source.getDescription());
        productDto.setPrice(source.getPrice());
        return productDto;
    }
}
