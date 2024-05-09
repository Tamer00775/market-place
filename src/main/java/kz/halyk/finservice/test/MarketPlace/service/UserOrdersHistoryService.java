package kz.halyk.finservice.test.MarketPlace.service;


import kz.halyk.finservice.test.MarketPlace.converter.orderDetails.OrderDetailsDtoConverter;
import kz.halyk.finservice.test.MarketPlace.converter.user.UserConverter;
import kz.halyk.finservice.test.MarketPlace.converter.user.UserDtoConverter;
import kz.halyk.finservice.test.MarketPlace.dto.cartItem.CartItemRequestDto;
import kz.halyk.finservice.test.MarketPlace.dto.orderDetails.OrderDetailsDto;
import kz.halyk.finservice.test.MarketPlace.dto.user.UserDto;
import kz.halyk.finservice.test.MarketPlace.entity.*;
import kz.halyk.finservice.test.MarketPlace.exception.MarketPlaceException;
import kz.halyk.finservice.test.MarketPlace.repository.*;
import kz.halyk.finservice.test.MarketPlace.utils.SecurityUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class UserOrdersHistoryService {

    private final UserDtoConverter userDtoConverter;
    private final OrderDetailsService orderDetailsService;
    private final ProductRepository productRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final UserPaymentRepository userPaymentRepository;
    private final PaymentStatusRepository paymentStatusRepository;
    private final PaymentDetailsRepository paymentDetailsRepository;
    private final OrderDetailsDtoConverter orderDetailsDtoConverter;

    public List<OrderDetailsDto> orderHistory() {
        User user = Objects.requireNonNull(SecurityUtils.getCurrentPerson());
        return orderDetailsRepository
                .findAllByUser(user)
                .stream().map(orderDetailsDtoConverter::convert)
                .collect(Collectors.toList());
    }

    public OrderDetailsDto orderHistoryMake(CartItemRequestDto cartItemRequestDto) {
        Long productId = cartItemRequestDto.getProductId();
        User user = Objects.requireNonNull(SecurityUtils.getCurrentPerson());
        Integer quantity = cartItemRequestDto.getQuantity();
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new MarketPlaceException(String.format("Product with id %d not found", productId),
                        LocalDateTime.now(),
                        HttpStatus.BAD_REQUEST)
                );
        if (product.getInventory().getQuantity() < quantity || quantity <= 0) {
            throw new MarketPlaceException(
                    "Error during ordering product with count...",
                    LocalDateTime.now(),
                    HttpStatus.CONFLICT
            );
        }
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setUser(user);
        orderDetails.setProduct(product);
        orderDetails.setQuantity(quantity);
        orderDetails.setTotalPrice(quantity * product.getPrice());

        orderDetails = orderDetailsRepository.save(orderDetails);

        UserPayment userPayment = userPaymentRepository.findByUser(user)
                .orElseThrow(() -> new MarketPlaceException(String.format("UserPayment with user %d not found", user),
                        LocalDateTime.now(),
                        HttpStatus.BAD_REQUEST)
                );
        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.setOrderDetails(orderDetails);
        paymentDetails.setUserPayment(userPayment);
        long randomInt = (int) (Math.random() * 4);
        paymentDetails.setPaymentStatus(
                paymentStatusRepository.findById(randomInt)
                        .orElseThrow(() -> new MarketPlaceException(String.format("UserPayment with user %d not found", user),
                                LocalDateTime.now(),
                                HttpStatus.BAD_REQUEST)
                        )
        );
        paymentDetails = paymentDetailsRepository.save(paymentDetails);

        return orderDetailsDtoConverter.convert(orderDetails);

    }
}
