package kz.halyk.finservice.test.MarketPlace.resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.halyk.finservice.test.MarketPlace.dto.product.ProductCreateDto;
import kz.halyk.finservice.test.MarketPlace.dto.product.ProductDto;
import kz.halyk.finservice.test.MarketPlace.dto.product.ProductSearchDto;
import kz.halyk.finservice.test.MarketPlace.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
@Validated
@Api("Functions to manipulate with product")
public class ProductResource {

    private final ProductService productService;

    @GetMapping("/{id}")
    @ApiOperation("Function to get product with id")
    public ResponseEntity<ProductDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @GetMapping
    @ApiOperation("Function to all products")
    public Page<ProductDto> findAll(Pageable pageable) {
        return productService.findAll(pageable);
    }

    @GetMapping("/search")
    @ApiOperation("Function to search products")
    public Page<ProductDto> search(ProductSearchDto productSearchDto, Pageable pageable) {
        return productService.search(productSearchDto, pageable);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Function to delete product with id")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) {
        productService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @PostMapping
    @ApiOperation("Function to create products")
    public ResponseEntity<ProductDto> create(@RequestBody @Valid ProductCreateDto productCreateDto) {
        return ResponseEntity.ok(productService.createProduct(productCreateDto));
    }

    @PutMapping("/{id}")
    @ApiOperation("Function to update products with id")
    public ResponseEntity<ProductDto> update(@PathVariable Long id, @RequestBody ProductCreateDto productDto) {
        return ResponseEntity.ok(productService.updateProduct(id, productDto));
    }

    @PostMapping("/{id}/subscribe")
    @ApiOperation("Function to subscribe user to sale of product")
    public ResponseEntity<HttpStatus> subscribeUserToProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.subscribeUserToProduct(id));
    }
}
