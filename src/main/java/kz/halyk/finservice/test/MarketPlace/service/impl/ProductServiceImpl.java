package kz.halyk.finservice.test.MarketPlace.service.impl;

import kz.halyk.finservice.test.MarketPlace.converter.product.ProductCreateConverter;
import kz.halyk.finservice.test.MarketPlace.converter.product.ProductDtoConverter;
import kz.halyk.finservice.test.MarketPlace.dto.product.ProductCreateDto;
import kz.halyk.finservice.test.MarketPlace.dto.product.ProductDto;
import kz.halyk.finservice.test.MarketPlace.dto.product.ProductSearchDto;
import kz.halyk.finservice.test.MarketPlace.entity.Product;
import kz.halyk.finservice.test.MarketPlace.repository.ProductRepository;
import kz.halyk.finservice.test.MarketPlace.service.ProductService;
import kz.halyk.finservice.test.MarketPlace.service.SpecificationService;
import kz.halyk.finservice.test.MarketPlace.exception.MarketPlaceException;
import kz.halyk.finservice.test.MarketPlace.util.validator.product_create.ProductCreateValidator;
import kz.halyk.finservice.test.MarketPlace.util.validator.product_search.ProductSearchValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final SpecificationService specificationService;
    private final CategoryServiceImpl categoryService;

    private final ProductRepository productRepository;

    private final ProductCreateConverter productCreateConverter;
    private final ProductDtoConverter productDtoConverter;

    private final List<ProductSearchValidator> searchValidatorList;
    private final List<ProductCreateValidator> createValidatorList;

    @Override
    @Transactional(readOnly = true)
    public ProductDto findById(Long id) {
        log.debug("start searching product with id {}", id);
        Optional<Product> byId = productRepository.findById(id);
        if (byId.isEmpty()) {
            throw new MarketPlaceException(String.format("Product with id %s not found", id),
                    LocalDateTime.now(),
                    HttpStatus.NOT_FOUND);
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
        createValidation(productCreateDto);
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
            product.setCategory(categoryService.findCategoryByCode(productDto.getCategoryCode()));
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
        searchValidatorList.forEach(productSearchValidator -> {
            productSearchValidator.validate(searchDto);
        });
        log.info("start searching products");
        Specification<Product> specification = specificationService.getProductSpecification(searchDto);
        return productRepository.findAll(specification, pageable).map(productDtoConverter::convert);
    }

    @Override
    public Optional<ProductDto> findByProductName(String productName) {
        Optional<Product> byProductName = productRepository.findByProductName(productName);
        if (byProductName.isEmpty()) {
            return Optional.empty();
        }
        return byProductName.map(productDtoConverter::convert);
    }

    private void createValidation(ProductCreateDto productCreateDto) {
        createValidatorList.forEach(validator -> {
            validator.validate(productCreateDto);
        });
    }
}
