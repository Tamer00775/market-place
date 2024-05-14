package kz.halyk.finservice.test.MarketPlace.converter.orderDetails;

import kz.halyk.finservice.test.MarketPlace.converter.paymentDetails.PaymentDetailsDtoConverter;
import kz.halyk.finservice.test.MarketPlace.converter.product.ProductDtoConverter;
import kz.halyk.finservice.test.MarketPlace.entity.PaymentDetails;
import kz.halyk.finservice.test.MarketPlace.repository.PaymentDetailsRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import kz.halyk.finservice.test.MarketPlace.dto.orderDetails.OrderDetailsDto;
import kz.halyk.finservice.test.MarketPlace.entity.OrderDetails;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

@Component
@AllArgsConstructor
public class OrderDetailsDtoConverter implements Converter<OrderDetails, OrderDetailsDto> {
    private final PaymentDetailsRepository paymentDetailsRepository;
    private final PaymentDetailsDtoConverter paymentDetailsDtoConverter;
    private final ProductDtoConverter productDtoConverter;
    @Override
    public OrderDetailsDto convert(OrderDetails source) {
        assert source != null;
        OrderDetailsDto dto = new OrderDetailsDto();
        dto.setId(source.getId());
        dto.setIsDeleted(source.getIsDeleted());
        dto.setQuantity(source.getQuantity());
        dto.setTotalPrice(source.getTotalPrice());
        dto.setProduct(productDtoConverter.convert(source.getProduct()));;
        return dto;
    }
}
