package kz.halyk.finservice.test.MarketPlace.converter.paymentDetails;

import kz.halyk.finservice.test.MarketPlace.dto.category.CategoryDto;
import kz.halyk.finservice.test.MarketPlace.dto.paymentDetails.PaymentDetailsDto;
import kz.halyk.finservice.test.MarketPlace.entity.Category;
import kz.halyk.finservice.test.MarketPlace.entity.PaymentDetails;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PaymentDetailsDtoConverter implements Converter<PaymentDetails, PaymentDetailsDto> {
    @Override
    public PaymentDetailsDto convert(PaymentDetails source) {
        PaymentDetailsDto dto = new PaymentDetailsDto();
        dto.setPaymentStatus(source.getPaymentStatus());
        return dto;
    }
}
