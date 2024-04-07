package kz.halyk.finservice.test.MarketPlace.service.impl;

import kz.halyk.finservice.test.MarketPlace.converter.user.UserConverter;
import kz.halyk.finservice.test.MarketPlace.converter.user.UserDtoConverter;
import kz.halyk.finservice.test.MarketPlace.dto.user.UserDto;
import kz.halyk.finservice.test.MarketPlace.entity.User;
import kz.halyk.finservice.test.MarketPlace.repository.UserRepository;
import kz.halyk.finservice.test.MarketPlace.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;


import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserDtoConverter userDtoConverter;
    private final UserConverter userConverter;

    @Override
    @Transactional(readOnly = true)
    public Page<UserDto> findAll(Pageable pageable) {
        return userRepository.findAll(pageable).map(userDtoConverter::convert);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public Optional<UserDto> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }
}
