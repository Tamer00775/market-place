package kz.halyk.finservice.test.MarketPlace.util.validator.product_create;

import kz.halyk.finservice.test.MarketPlace.dto.product.ProductCreateDto;
import kz.halyk.finservice.test.MarketPlace.dto.product.ProductDto;
import kz.halyk.finservice.test.MarketPlace.entity.Product;
import kz.halyk.finservice.test.MarketPlace.exception.MarketPlaceException;
import kz.halyk.finservice.test.MarketPlace.repository.ProductRepository;
import kz.halyk.finservice.test.MarketPlace.service.ProductService;
import kz.halyk.finservice.test.MarketPlace.service.impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductNameValidator implements ProductCreateValidator {
    @Autowired
    private ProductRepository productRepository;


    @Override
    public void validate(ProductCreateDto productCreateDto) {
        Optional<Product> byProductName = productRepository.findByProductName(productCreateDto.getProductName());
        if (byProductName.isPresent()) {
            throw new MarketPlaceException("Product Name must be unique.",
                    LocalDateTime.now(),
                    HttpStatus.BAD_REQUEST);
        }
    }
}
