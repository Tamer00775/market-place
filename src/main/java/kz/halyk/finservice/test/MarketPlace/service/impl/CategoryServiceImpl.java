package kz.halyk.finservice.test.MarketPlace.service.impl;

import kz.halyk.finservice.test.MarketPlace.converter.category.CategoryConverter;
import kz.halyk.finservice.test.MarketPlace.converter.category.CategoryDtoConverter;
import kz.halyk.finservice.test.MarketPlace.dto.category.CategoryDto;
import kz.halyk.finservice.test.MarketPlace.entity.Category;
import kz.halyk.finservice.test.MarketPlace.repository.CategoryRepository;
import kz.halyk.finservice.test.MarketPlace.service.CategoryService;
import kz.halyk.finservice.test.MarketPlace.exception.MarketPlaceException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryServiceImpl implements CategoryService {
    CategoryRepository categoryRepository;

    CategoryDtoConverter categoryDtoConverter;
    CategoryConverter categoryConverter;

    @Override
    @Transactional
    public CategoryDto create(CategoryDto categoryDto) {
        Category convert = categoryConverter.convert(categoryDto);
        if (convert == null) {
            throw new IllegalArgumentException("error when converting dto to entity");
        }
        return categoryDtoConverter.convert(categoryRepository.save(convert));
    }
    @Override
    @Transactional(readOnly = true)
    public CategoryDto findById(Long id) {
        return categoryDtoConverter.convert(categoryRepository.findById(id).orElseThrow(
                () -> new MarketPlaceException(String.format("Category with id %s not found", id),
                        LocalDateTime.now(),
                        HttpStatus.BAD_REQUEST)
        ));
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryDto findByCode(String code) {
        return categoryDtoConverter.convert(categoryRepository.findByCategoryCode(code).orElseThrow(
                () -> new MarketPlaceException(String.format("Category with id %s not found", code),
                        LocalDateTime.now(),
                        HttpStatus.BAD_REQUEST)
        ));
    }

    @Transactional(readOnly = true)
    public Category findCategoryByCode(String code) {
        return categoryRepository.findByCategoryCode(code).orElseThrow(
                () -> new MarketPlaceException(String.format("Category with code %d not found", code),
                        LocalDateTime.now(),
                        HttpStatus.BAD_REQUEST));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream().map(categoryDtoConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CategoryDto> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable).map(categoryDtoConverter::convert);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
