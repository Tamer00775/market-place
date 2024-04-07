package kz.halyk.finservice.test.MarketPlace.service;

import kz.halyk.finservice.test.MarketPlace.dto.user.AuthDto;
import kz.halyk.finservice.test.MarketPlace.dto.user.UserDto;
import kz.halyk.finservice.test.MarketPlace.dto.user.UserRegistrationDto;

import java.util.Map;

public interface AuthService {
    Map<String, String> login(AuthDto authDto);

    UserDto register(UserRegistrationDto userRegistrationDto);
}
