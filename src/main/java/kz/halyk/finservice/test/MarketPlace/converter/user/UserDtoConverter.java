package kz.halyk.finservice.test.MarketPlace.converter.user;

import kz.halyk.finservice.test.MarketPlace.dto.user.UserDto;
import kz.halyk.finservice.test.MarketPlace.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter implements Converter<User, UserDto> {

    @Override
    public UserDto convert(User source) {
        UserDto userDto = new UserDto();
        userDto.setEmail(source.getEmail());
        userDto.setLogin(source.getLogin());
        userDto.setFirstName(source.getFirstName());
        userDto.setLastName(source.getLastName());
        if (source.getMiddleName() != null) {
            userDto.setMiddleName(source.getMiddleName());
        }
        return userDto;
    }
}
