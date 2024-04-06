package kz.halyk.finservice.test.MarketPlace.service;

import kz.halyk.finservice.test.MarketPlace.dto.product.ProductSearchDto;
import kz.halyk.finservice.test.MarketPlace.entity.Category;
import kz.halyk.finservice.test.MarketPlace.entity.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class SpecificationService {
    public Specification<Product> getProductSpecification(ProductSearchDto searchDto) {
        return Specification.where(((root, query, criteriaBuilder) ->  {
            List<Predicate> predicates = new ArrayList<>();
            if (searchDto.getPriceFrom() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), searchDto.getPriceFrom()));
            }
            if (searchDto.getPriceTo() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), searchDto.getPriceTo()));
            }
            if (searchDto.getProductNameQuery() != null) {
                String searchQuery = searchDto.getProductNameQuery().toLowerCase();
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("productName")), "%" + searchQuery + "%"));
            }
            if (searchDto.getCategoryCode() != null) {
                Join<Product, Category> categoryJoin = root.join("category", JoinType.INNER);
                predicates.add(criteriaBuilder.equal(categoryJoin.get("categoryCode"), searchDto.getCategoryCode()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }));
    }
}
