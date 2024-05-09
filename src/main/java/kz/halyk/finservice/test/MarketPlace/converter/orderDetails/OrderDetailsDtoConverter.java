package kz.halyk.finservice.test.MarketPlace.converter.orderDetails;

import org.springframework.core.convert.converter.Converter;
import kz.halyk.finservice.test.MarketPlace.dto.orderDetails.OrderDetailsDto;
import kz.halyk.finservice.test.MarketPlace.entity.OrderDetails;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

@Component
public class OrderDetailsDtoConverter implements Converter<OrderDetails, OrderDetailsDto> {
    @Override
    public OrderDetailsDto convert(OrderDetails source) {
        OrderDetailsDto dto = new OrderDetailsDto();
        dto.setId(source.getId());
        dto.setIsDeleted(source.getIsDeleted());
        dto.setQuantity(source.getQuantity());
        dto.setTotalPrice(source.getTotalPrice());
        dto.setUser(source.getUser());
        dto.setProduct(source.getProduct());
        return null;
    }
}
