package kz.halyk.finservice.test.MarketPlace.service;


import kz.halyk.finservice.test.MarketPlace.converter.orderDetails.OrderDetailsDtoConverter;
import kz.halyk.finservice.test.MarketPlace.converter.paymentDetails.PaymentDetailsDtoConverter;
import kz.halyk.finservice.test.MarketPlace.converter.user.UserDtoConverter;
import kz.halyk.finservice.test.MarketPlace.dto.orderDetails.OrderDetailsDto;
import kz.halyk.finservice.test.MarketPlace.dto.orderDetails.OrderDetailsRequestDto;
import kz.halyk.finservice.test.MarketPlace.entity.*;
import kz.halyk.finservice.test.MarketPlace.exception.MarketPlaceException;
import kz.halyk.finservice.test.MarketPlace.repository.*;
import kz.halyk.finservice.test.MarketPlace.utils.SecurityUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final PaymentDetailsDtoConverter paymentDetailsDtoConverter;
    private final OrderDetailsRepository orderDetailsRepository;
    private final UserPaymentRepository userPaymentRepository;
    private final PaymentStatusRepository paymentStatusRepository;
    private final PaymentDetailsRepository paymentDetailsRepository;
    private final OrderDetailsDtoConverter orderDetailsDtoConverter;
    private final CartItemRepository cartItemRepository;

    public List<OrderDetailsDto> orderHistory() {
        User user = Objects.requireNonNull(SecurityUtils.getCurrentPerson());
        return orderDetailsRepository
                .findAllByUser(user)
                .stream().map(orderDetailsDtoConverter::convert)
                .collect(Collectors.toList());
    }

    public OrderDetailsDto orderHistoryMake(OrderDetailsRequestDto orderDetailsRequestDto) {
        User user = Objects.requireNonNull(SecurityUtils.getCurrentPerson());
        Long cartItemId = orderDetailsRequestDto.getCardItemId();
        CartItem cartItem = cartItemRepository
                .findById(cartItemId)
                .orElseThrow(() -> new MarketPlaceException(String.format("CartItem with id %d not found", cartItemId),
                        LocalDateTime.now(),
                        HttpStatus.NOT_FOUND)
                );
        OrderDetails orderDetails = generateOrderDetails(cartItem);
        UserPayment userPayment = userPaymentRepository.findByUser(user)
                .orElseThrow(() -> new MarketPlaceException(String.format("UserPayment with user %d not found", user),
                        LocalDateTime.now(),
                        HttpStatus.NOT_FOUND)
                );
        PaymentDetails paymentDetails = generatePaymentDetails(orderDetails,userPayment);
        long randomInt = 2;
        paymentDetails.setPaymentStatus(
                paymentStatusRepository.findById(randomInt)
                        .orElseThrow(() -> new MarketPlaceException(String.format("UserPayment with user %s not found", user.getLogin()),
                                LocalDateTime.now(),
                                HttpStatus.BAD_REQUEST)
                        )
        );
        paymentDetailsRepository.save(paymentDetails);
        OrderDetailsDto convert = orderDetailsDtoConverter.convert(orderDetails);
        if (paymentDetailsDtoConverter.convert(paymentDetails) != null) {
            convert.setPaymentDetailsDto(paymentDetailsDtoConverter.convert(paymentDetails));
        }
        return convert;

    }

    private PaymentDetails generatePaymentDetails(OrderDetails orderDetails, UserPayment userPayment) {
        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.setOrderDetails(orderDetails);
        paymentDetails.setUserPayment(userPayment);
        return paymentDetails;
    }

    private OrderDetails generateOrderDetails(CartItem cartItem) {
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setUser(cartItem.getUser());
        orderDetails.setProduct(cartItem.getProduct());
        orderDetails.setQuantity(cartItem.getQuantity());
        orderDetails.setTotalPrice(cartItem.getQuantity() * cartItem.getProduct().getPrice());
        return orderDetailsRepository.save(orderDetails);
    }
}
