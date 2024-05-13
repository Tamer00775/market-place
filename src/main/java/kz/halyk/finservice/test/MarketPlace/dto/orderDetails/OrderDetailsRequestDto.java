package kz.halyk.finservice.test.MarketPlace.dto.orderDetails;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class OrderDetailsRequestDto {
    @NotNull(message = "CartItemId ID cannot be null")
    private Long cartItemId;
}