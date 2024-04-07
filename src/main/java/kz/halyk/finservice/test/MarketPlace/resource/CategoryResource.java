package kz.halyk.finservice.test.MarketPlace.resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.halyk.finservice.test.MarketPlace.dto.category.CategoryDto;
import kz.halyk.finservice.test.MarketPlace.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
@Validated
@Api("Resource about categories of product")
public class CategoryResource {
    private final CategoryService categoryService;

    @GetMapping
    @ApiOperation("Function to getting all categories")
    public ResponseEntity<Page<CategoryDto>> findAll(Pageable pageable) {
        return ResponseEntity.ok(categoryService.findAll(pageable));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Function to delete category by id")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) {
        categoryService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @PostMapping
    @ApiOperation("Function to create category")
    public ResponseEntity<CategoryDto> create(@RequestBody @Valid CategoryDto categoryDto) {
        return ResponseEntity.ok(categoryService.create(categoryDto));
    }

    @GetMapping("/{categoryCode}")
    @ApiOperation("Function to get category by code")
    public ResponseEntity<CategoryDto> findByCode(@PathVariable String categoryCode) {
        return ResponseEntity.ok(categoryService.findByCode(categoryCode));
    }
}
