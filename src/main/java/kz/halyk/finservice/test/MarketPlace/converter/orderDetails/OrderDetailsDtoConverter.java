package kz.halyk.finservice.test.MarketPlace.converter.orderDetails;

import kz.halyk.finservice.test.MarketPlace.converter.paymentDetails.PaymentDetailsDtoConverter;
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
    @Override
    public OrderDetailsDto convert(OrderDetails source) {
        assert source != null;
        OrderDetailsDto dto = new OrderDetailsDto();
        dto.setId(source.getId());
        dto.setIsDeleted(source.getIsDeleted());
        dto.setQuantity(source.getQuantity());
        dto.setTotalPrice(source.getTotalPrice());
        dto.setProduct(source.getProduct());
        PaymentDetails paymentDetails = paymentDetailsRepository
                .findAll()
                        .stream()
                           .filter(paymentDetails1 -> paymentDetails1.getId().equals(source.getId()))
                .findAny()
                .orElse(null);
        System.out.println("paymentDetails" + paymentDetails);
        dto.setPaymentDetailsDto(paymentDetailsDtoConverter.convert(paymentDetails));
        return dto;
    }
}
