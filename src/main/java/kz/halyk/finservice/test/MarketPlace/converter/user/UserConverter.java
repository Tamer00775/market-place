package kz.halyk.finservice.test.MarketPlace.converter.user;

import kz.halyk.finservice.test.MarketPlace.dto.user.UserDto;
import kz.halyk.finservice.test.MarketPlace.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements Converter<UserDto, User> {
    @Override
    public User convert(UserDto source) {
        User user = new User();
        return user;
    }
}
