package kz.halyk.finservice.test.MarketPlace.endpoints;


import kz.halyk.finservice.test.MarketPlace.dto.cartItem.CartItemRequestDto;
import kz.halyk.finservice.test.MarketPlace.dto.orderDetails.OrderDetailsDto;
import kz.halyk.finservice.test.MarketPlace.dto.orderDetails.OrderDetailsRequestDto;
import kz.halyk.finservice.test.MarketPlace.dto.paymentDetails.PaymentDetailsDto;
import kz.halyk.finservice.test.MarketPlace.entity.OrderDetails;
import kz.halyk.finservice.test.MarketPlace.entity.User;
import kz.halyk.finservice.test.MarketPlace.service.PaymentDetailsService;
import kz.halyk.finservice.test.MarketPlace.service.UserOrdersHistoryService;
import kz.halyk.finservice.test.MarketPlace.service.impl.PaymentDetailsServiceImpl;
import kz.halyk.finservice.test.MarketPlace.utils.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/order")
@AllArgsConstructor
public class UserOrdersHistoryResource {

    private final UserOrdersHistoryService service;
    @GetMapping("")
    public ResponseEntity<List<OrderDetailsDto>> orderHistory() {
        return ResponseEntity.ok().body(service.orderHistory());
    }

    @PostMapping("/make")
    public ResponseEntity<OrderDetailsDto> orderHistoryMake(@RequestBody OrderDetailsRequestDto orderDetailsDto
    ) {
        return ResponseEntity.ok().body(service.orderHistoryMake(orderDetailsDto));
    }




}
