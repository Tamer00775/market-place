package kz.halyk.finservice.test.MarketPlace.endpoints;


import kz.halyk.finservice.test.MarketPlace.dto.userPayment.UserPaymentDto;
import kz.halyk.finservice.test.MarketPlace.entity.UserPayment;
import kz.halyk.finservice.test.MarketPlace.service.UserPaymentService;
import kz.halyk.finservice.test.MarketPlace.service.impl.UserPaymentServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/payment")
@AllArgsConstructor
public class UserPaymentResource {

    private final UserPaymentServiceImpl userPaymentService;

    @GetMapping()
    public ResponseEntity<UserPaymentDto> userPaymentDtoResponseEntity() {
        return ResponseEntity.ok().body(userPaymentService.findByUser());
    }
}
