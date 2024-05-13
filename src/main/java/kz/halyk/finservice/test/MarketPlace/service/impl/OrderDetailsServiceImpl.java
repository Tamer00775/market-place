package kz.halyk.finservice.test.MarketPlace.service.impl;


import kz.halyk.finservice.test.MarketPlace.converter.orderDetails.OrderDetailsDtoConverter;
import kz.halyk.finservice.test.MarketPlace.dto.orderDetails.OrderDetailsDto;
import kz.halyk.finservice.test.MarketPlace.entity.OrderDetails;
import kz.halyk.finservice.test.MarketPlace.entity.User;
import kz.halyk.finservice.test.MarketPlace.exception.MarketPlaceException;
import kz.halyk.finservice.test.MarketPlace.repository.OrderDetailsRepository;
import kz.halyk.finservice.test.MarketPlace.service.OrderDetailsService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.criterion.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderDetailsServiceImpl implements OrderDetailsService {

    private final OrderDetailsRepository orderDetailsRepository;

    private final OrderDetailsDtoConverter orderDetailsDtoConverter;

    @Override
    public OrderDetailsDto findById(Long id) {
        return orderDetailsRepository
                .findById(id)
                .map(orderDetailsDtoConverter::convert)
                .orElseThrow(
                () -> new MarketPlaceException(String.format("OrderDetails with id %s not found", id),
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST));
    }

    @Override
    public List<OrderDetailsDto> findAll() {
        return orderDetailsRepository
                .findAll()
                .stream()
                .map(orderDetailsDtoConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDetailsDto> findAllByUser(User user) {
        return orderDetailsRepository
                .findAllByUser(user)
                .stream()
                .map(orderDetailsDtoConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public Page<OrderDetailsDto> findAll(Pageable pageable) {
        return orderDetailsRepository
                .findAll(pageable)
                .map(orderDetailsDtoConverter::convert);
    }


    @Override
    public void deleteById(Long id) {
        orderDetailsRepository.deleteById(id);
    }

    @Override
    public OrderDetails create(OrderDetailsDto dto) {
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setIsDeleted(dto.getIsDeleted());
        orderDetails.setQuantity(dto.getQuantity());
        orderDetails.setTotalPrice(dto.getTotalPrice());
        orderDetails.setUser(dto.getUser());
        orderDetails.setProduct(dto.getProduct());
        return orderDetails;
    }
}
