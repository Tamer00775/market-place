package kz.halyk.finservice.test.MarketPlace.service.impl;

import kz.halyk.finservice.test.MarketPlace.converter.ProductCreateConverter;
import kz.halyk.finservice.test.MarketPlace.converter.ProductDtoConverter;
import kz.halyk.finservice.test.MarketPlace.dto.product.ProductCreateDto;
import kz.halyk.finservice.test.MarketPlace.dto.product.ProductDto;
import kz.halyk.finservice.test.MarketPlace.dto.product.ProductSearchDto;
import kz.halyk.finservice.test.MarketPlace.entity.Product;
import kz.halyk.finservice.test.MarketPlace.repository.ProductRepository;
import kz.halyk.finservice.test.MarketPlace.service.ProductService;
import kz.halyk.finservice.test.MarketPlace.service.SpecificationService;
import kz.halyk.finservice.test.MarketPlace.util.MarketPlaceException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ProductServiceImpl implements ProductService {
    SpecificationService specificationService;
    CategoryServiceImpl categoryService;

    ProductRepository productRepository;

    ProductCreateConverter productCreateConverter;
    ProductDtoConverter productDtoConverter;

    @Override
    @Transactional(readOnly = true)
    public ProductDto findById(Long id) {
        log.debug("start searching product with id {}", id);
        Optional<Product> byId = productRepository.findById(id);
        if (byId.isEmpty()) {
            throw new MarketPlaceException(String.format("Product with id %s not found", id));
        }
        return productDtoConverter.convert(byId.get());
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        log.debug("start deleting product with id {}", id);
        productRepository.deleteById(id);
    }

    @Override
    @Transactional
    public ProductDto createProduct(ProductCreateDto productCreateDto) {
        Product convert = productCreateConverter.convert(productCreateDto);
        assert convert != null;
        return productDtoConverter.convert(productRepository.save(convert));
    }

    @Override
    @Transactional
    public ProductDto updateProduct(Long id, ProductCreateDto productDto) {
        Optional<Product> byId = productRepository.findById(id);
        if (byId.isEmpty()) {
            throw new MarketPlaceException(String.format("Product with id %s not found", id),
                    LocalDateTime.now(),
                    HttpStatus.NOT_FOUND);
        }
        Product product = byId.get();
        if (product.getProductName() != null && !productDto.getProductName().equals(product.getProductName())) {
            product.setProductName(productDto.getProductName());
        }
        if (productDto.getCategoryCode() != null && !productDto.getCategoryCode().equals(product.getCategory().getCategoryCode())) {
            product.setCategory(categoryService.findCategoryByCode(productDto.getCategoryCode().name()));
        }
        if (productDto.getPrice() != null && productDto.getPrice().equals(product.getPrice())) {
            product.setPrice(productDto.getPrice());
        }
        return productDtoConverter.convert(productRepository.save(product));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductDto> findAll(Pageable pageable) {
        return productRepository.findAll(pageable).map(productDtoConverter::convert);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductDto> search(ProductSearchDto searchDto, Pageable pageable) {
        log.info("start searching products");
        Specification<Product> specification = specificationService.getProductSpecification(searchDto);
        return productRepository.findAll(specification, pageable).map(productDtoConverter::convert);
    }
}
