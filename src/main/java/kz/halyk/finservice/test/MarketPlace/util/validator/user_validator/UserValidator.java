package kz.halyk.finservice.test.MarketPlace.util.validator.user_validator;

import kz.halyk.finservice.test.MarketPlace.dto.user.UserRegistrationDto;
import org.springframework.stereotype.Component;


@Component
public interface UserValidator {

    void validateRegistrationDto(UserRegistrationDto userRegistrationDto);
}
