package kz.halyk.finservice.test.MarketPlace.service.impl;

import kz.halyk.finservice.test.MarketPlace.converter.CategoryConverter;
import kz.halyk.finservice.test.MarketPlace.converter.CategoryDtoConverter;
import kz.halyk.finservice.test.MarketPlace.dto.category.CategoryDto;
import kz.halyk.finservice.test.MarketPlace.entity.Category;
import kz.halyk.finservice.test.MarketPlace.enums.CategoryCode;
import kz.halyk.finservice.test.MarketPlace.repository.CategoryRepository;
import kz.halyk.finservice.test.MarketPlace.service.CategoryService;
import kz.halyk.finservice.test.MarketPlace.util.MarketPlaceException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
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
                () -> new MarketPlaceException(String.format("Category with id %s not found", id))
        ));
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryDto findByCode(String code) {
        return categoryDtoConverter.convert(categoryRepository.findByCategoryCode(CategoryCode.valueOf(code)).orElseThrow(
                () -> new MarketPlaceException(String.format("Category with id %s not found", code))
        ));
    }

    @Transactional(readOnly = true)
    public Category findCategoryByCode(String code) {
        return categoryRepository.findByCategoryCode(CategoryCode.valueOf(code)).orElseThrow(
                () -> new MarketPlaceException(String.format("Category with code %d not found", code))
        );
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
