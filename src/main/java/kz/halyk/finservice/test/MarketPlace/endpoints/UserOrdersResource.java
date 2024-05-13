package kz.halyk.finservice.test.MarketPlace.endpoints;


import kz.halyk.finservice.test.MarketPlace.dto.cartItem.CartItemRequestDto;
import kz.halyk.finservice.test.MarketPlace.dto.orderDetails.OrderDetailsDto;
import kz.halyk.finservice.test.MarketPlace.dto.orderDetails.OrderDetailsRequestDto;
import kz.halyk.finservice.test.MarketPlace.service.UserOrdersHistoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/order")
@AllArgsConstructor
@Slf4j
public class UserOrdersResource {

    private final UserOrdersHistoryService service;
    @GetMapping("")
    public ResponseEntity<List<OrderDetailsDto>> orderHistory() {
        return ResponseEntity.ok().body(service.orderHistory());
    }

    @PostMapping("/make")
    public ResponseEntity<OrderDetailsDto> orderHistoryMake(
            @RequestBody @Validated OrderDetailsRequestDto orderDetailsRequestDto
    ) {
        return ResponseEntity.ok().body(service.orderHistoryMake(orderDetailsRequestDto));
    }
}
