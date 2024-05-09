package kz.halyk.finservice.test.MarketPlace.service;

import kz.halyk.finservice.test.MarketPlace.dto.category.CategoryDto;
import kz.halyk.finservice.test.MarketPlace.dto.orderDetails.OrderDetailsDto;
import kz.halyk.finservice.test.MarketPlace.entity.OrderDetails;
import kz.halyk.finservice.test.MarketPlace.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderDetailsService {

    OrderDetailsDto findById(Long id);

    List<OrderDetailsDto> findAll();

    List<OrderDetailsDto> findAllByUser(User user);

    Page<OrderDetailsDto> findAll(Pageable pageable);


    void deleteById(Long id);

    OrderDetails create(OrderDetailsDto dto);
}