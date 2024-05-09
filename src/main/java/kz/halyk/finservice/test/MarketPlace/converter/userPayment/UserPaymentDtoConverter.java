package kz.halyk.finservice.test.MarketPlace.converter.userPayment;

import kz.halyk.finservice.test.MarketPlace.dto.category.CategoryDto;
import kz.halyk.finservice.test.MarketPlace.dto.userPayment.UserPaymentDto;
import kz.halyk.finservice.test.MarketPlace.entity.Category;
import kz.halyk.finservice.test.MarketPlace.entity.UserPayment;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class UserPaymentDtoConverter implements Converter<UserPayment, UserPaymentDto> {
    @Override
    public UserPaymentDto convert(UserPayment source) {
        UserPaymentDto dto = new UserPaymentDto();
        dto.setId(source.getId());
        dto.setPaymentType(source.getPaymentType());
        dto.setProvider(source.getProvider());
        dto.setAccountNo(source.getAccountNo());
        dto.setExpiry(source.getExpiry());
        dto.setIsDeleted(source.getIsDeleted());
        dto.setUser(source.getUser());
        return dto;
    }
}
