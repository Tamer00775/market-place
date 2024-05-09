package kz.halyk.finservice.test.MarketPlace.endpoints;


import kz.halyk.finservice.test.MarketPlace.dto.orderDetails.OrderDetailsDto;
import kz.halyk.finservice.test.MarketPlace.dto.paymentDetails.PaymentDetailsDto;
import kz.halyk.finservice.test.MarketPlace.entity.OrderDetails;
import kz.halyk.finservice.test.MarketPlace.entity.User;
import kz.halyk.finservice.test.MarketPlace.service.PaymentDetailsService;
import kz.halyk.finservice.test.MarketPlace.service.UserOrdersHistoryService;
import kz.halyk.finservice.test.MarketPlace.service.impl.PaymentDetailsServiceImpl;
import kz.halyk.finservice.test.MarketPlace.utils.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/history")
@AllArgsConstructor
public class UserOrdersHistoryResource {

    private final UserOrdersHistoryService service;
    private final PaymentDetailsServiceImpl paymentDetailsService;
    @GetMapping("/order")
    public ResponseEntity<List<OrderDetailsDto>> orderHistory() {
        return ResponseEntity.ok().body(service.orderHistory());
    }
    @GetMapping("/payment")
    public ResponseEntity<List<PaymentDetailsDto>> paymentHistory() {
        return ResponseEntity.ok().body(paymentDetailsService.paymentHistory());
    }


}
