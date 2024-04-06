package kz.halyk.finservice.test.MarketPlace.service;

import kz.halyk.finservice.test.MarketPlace.dto.product.ProductCreateDto;
import kz.halyk.finservice.test.MarketPlace.dto.product.ProductDto;
import kz.halyk.finservice.test.MarketPlace.dto.product.ProductSearchDto;
import kz.halyk.finservice.test.MarketPlace.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    ProductDto findById(Long id);

    void deleteById(Long id);

    ProductDto createProduct(ProductCreateDto productCreateDto);

    ProductDto updateProduct(Long id, ProductCreateDto productDto);

    Page<ProductDto> findAll(Pageable pageable);

    Page<ProductDto> search(ProductSearchDto searchDto, Pageable pageable);
}
