package kz.halyk.finservice.test.MarketPlace.util.validator.user_validator;

import kz.halyk.finservice.test.MarketPlace.dto.user.UserDto;
import kz.halyk.finservice.test.MarketPlace.dto.user.UserRegistrationDto;
import kz.halyk.finservice.test.MarketPlace.exception.MarketPlaceException;
import kz.halyk.finservice.test.MarketPlace.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserEmailValidator implements UserValidator {

    private final UserService userService;
    @Override
    public void validateRegistrationDto(UserRegistrationDto userRegistrationDto) {
        if (userRegistrationDto.getEmail() != null) {
            Optional<UserDto> byEmail = userService.findByEmail(userRegistrationDto.getEmail());
            if (byEmail.isPresent()) {
                throw new MarketPlaceException(String.format("Email %s is already exists",
                        userRegistrationDto.getEmail()), LocalDateTime.now(), HttpStatus.BAD_REQUEST);
            }
        }
    }
}
