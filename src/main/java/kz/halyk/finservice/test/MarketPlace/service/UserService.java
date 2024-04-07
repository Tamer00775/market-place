package kz.halyk.finservice.test.MarketPlace.service;

import kz.halyk.finservice.test.MarketPlace.dto.user.UserDto;

import kz.halyk.finservice.test.MarketPlace.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface UserService {

    Page<UserDto> findAll(Pageable pageable);

    Optional<User> findByLogin(String login);

    Optional<UserDto> findByEmail(String email);

    User save(User user);


}
