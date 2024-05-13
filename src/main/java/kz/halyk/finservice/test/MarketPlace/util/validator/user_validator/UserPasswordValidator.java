package kz.halyk.finservice.test.MarketPlace.util.validator.user_validator;

import kz.halyk.finservice.test.MarketPlace.dto.user.UserRegistrationDto;
import kz.halyk.finservice.test.MarketPlace.exception.MarketPlaceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserPasswordValidator implements UserValidator {
    @Value("${password-policy.min-characters}")
    private Long size;

    @Value("${password-policy.useUpperCase}")
    private Boolean useUpperCase;

    @Value("${password-policy.useSpecificSymbols}")
    private Boolean useSpecificSymbols;

    @Value("${password-policy.useNumbers}")
    private Boolean useNumbers;


    @Override
    public void validateRegistrationDto(UserRegistrationDto userRegistrationDto) {
        String password = userRegistrationDto.getPassword();
        if (size != null && password.length() < size)  {
            throw new MarketPlaceException("Password must be more than " + size + " characters!", LocalDateTime.now(),
                    HttpStatus.BAD_REQUEST);
        }
        if (useUpperCase != null && useUpperCase) {
            boolean hasUppercase = password.chars().anyMatch(Character::isUpperCase);
            if (!hasUppercase) {
                throw new MarketPlaceException("Password must have uppercase's", LocalDateTime.now(),
                        HttpStatus.BAD_REQUEST);
            }
        }
        if (useNumbers != null && useNumbers) {
            boolean hasNumbers = password.chars().anyMatch(Character::isDigit);
            if (!hasNumbers) {
                throw new MarketPlaceException("Password must have digits", LocalDateTime.now(),
                        HttpStatus.BAD_REQUEST);
            }
        }
    }
}
