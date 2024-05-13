package kz.halyk.finservice.test.MarketPlace.endpoints;

import kz.halyk.finservice.test.MarketPlace.dto.cartItem.CartItemDto;
import kz.halyk.finservice.test.MarketPlace.dto.cartItem.CartItemRequestDto;
import kz.halyk.finservice.test.MarketPlace.service.impl.CartItemServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/cartItem")
@AllArgsConstructor
@Slf4j
public class CartItemResource {

    private final CartItemServiceImpl cartItemService;

    @GetMapping
    public ResponseEntity<List<CartItemDto>> cartItem(
    ) {
        log.info("Fetching all cart items for the user");
        List<CartItemDto> cartItems = cartItemService.findAllByUser();
        return ResponseEntity.ok().body(cartItems);
    }

    @PostMapping("/updateItem")
    public ResponseEntity<CartItemDto> updateItem(
            @RequestBody @Validated CartItemRequestDto cartItemRequestDto
    ) {
        log.info("Updating cart item with request: {}", cartItemRequestDto);
        CartItemDto updatedItem = cartItemService.updateItem(cartItemRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedItem);
    }
}
