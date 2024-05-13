package kz.halyk.finservice.test.MarketPlace.endpoints;


import kz.halyk.finservice.test.MarketPlace.dto.cartItem.CartItemDto;
import kz.halyk.finservice.test.MarketPlace.dto.cartItem.CartItemRequestDto;
import kz.halyk.finservice.test.MarketPlace.service.impl.CartItemServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/cartItem")
@AllArgsConstructor
public class CartItemResource {

    private final CartItemServiceImpl cartItemService;

    @GetMapping
    public ResponseEntity<List<CartItemDto>> cartItem() {
        return ResponseEntity.ok().body(cartItemService.findAllByUser());
    }
    @PostMapping("/updateItem")
    public ResponseEntity<CartItemDto> updateItem(
            @RequestBody CartItemRequestDto cartItemRequestDto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cartItemService.updateItem(cartItemRequestDto));
    }

}
