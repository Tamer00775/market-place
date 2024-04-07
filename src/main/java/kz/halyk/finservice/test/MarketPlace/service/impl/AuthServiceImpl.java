package kz.halyk.finservice.test.MarketPlace.service.impl;

import kz.halyk.finservice.test.MarketPlace.converter.user.UserConverter;
import kz.halyk.finservice.test.MarketPlace.converter.user.UserDtoConverter;
import kz.halyk.finservice.test.MarketPlace.dto.user.AuthDto;
import kz.halyk.finservice.test.MarketPlace.dto.user.UserDto;
import kz.halyk.finservice.test.MarketPlace.dto.user.UserRegistrationDto;
import kz.halyk.finservice.test.MarketPlace.entity.Role;
import kz.halyk.finservice.test.MarketPlace.entity.User;
import kz.halyk.finservice.test.MarketPlace.entity.UserAuth;
import kz.halyk.finservice.test.MarketPlace.entity.UserRole;
import kz.halyk.finservice.test.MarketPlace.enums.UserRoleEnum;
import kz.halyk.finservice.test.MarketPlace.enums.UserStatus;
import kz.halyk.finservice.test.MarketPlace.repository.RoleRepository;
import kz.halyk.finservice.test.MarketPlace.repository.UserAuthRepository;
import kz.halyk.finservice.test.MarketPlace.repository.UserRepository;
import kz.halyk.finservice.test.MarketPlace.repository.UserRoleRepository;
import kz.halyk.finservice.test.MarketPlace.security.JwtUtil;
import kz.halyk.finservice.test.MarketPlace.service.AuthService;
import kz.halyk.finservice.test.MarketPlace.service.PersonDetailsService;
import kz.halyk.finservice.test.MarketPlace.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PersonDetailsService personDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserAuthRepository userAuthRepository;

    private final UserDtoConverter userDtoConverter;

    @Override
    public Map<String, String> login(AuthDto authDto) {
        try {
            UserDetails userDetails = personDetailsService.loadUserByUsername(authDto.getLogin());
            if (passwordEncoder.matches(authDto.getPassword(), userDetails.getPassword())) {
                String token = jwtUtil.generateToken(authDto.getLogin());
                return Map.of("token", token);
            }
        } catch (UsernameNotFoundException e) {
            return Map.of("Message", e.getMessage());
        }
        return Map.of("message", "incorrect credentials!");
    }

    @Override
    @Transactional
    public UserDto register(UserRegistrationDto userRegistrationDto) {
        User user = fillUserInformation(userRegistrationDto);
        UserAuth userAuth = fillUserAuth(user, userRegistrationDto);
        UserRole userRole = fillUserRole(user);
        createUser(user, userAuth, userRole);

        return userDtoConverter.convert(user);
    }
    private void createUser(User user, UserAuth userAuth, UserRole userRole) {
        userService.save(user);
        userRoleRepository.save(userRole);
        userAuthRepository.save(userAuth);
    }

    private UserRole fillUserRole(User user) {
        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setIsActive(true);
        userRole.setIsDeleted(false);
        Optional<Role> byCode = roleRepository.findByCode(UserRoleEnum.ADMIN.name());
        byCode.ifPresent(userRole::setRole);
        return userRole;
    }

    private UserAuth fillUserAuth(User user, UserRegistrationDto userRegistrationDto) {
        UserAuth userAuth = new UserAuth();
        userAuth.setStatus(UserStatus.REGISTERED);
        userAuth.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
        userAuth.setUser(user);
        return userAuth;
    }

    private User fillUserInformation(UserRegistrationDto userRegistrationDto) {
        User user = new User();
        user.setFirstName(userRegistrationDto.getFirstName());
        if (userRegistrationDto.getMiddleName() != null) {
            user.setMiddleName(userRegistrationDto.getMiddleName());
        }
        user.setLastName(userRegistrationDto.getLastName());
        String email = userRegistrationDto.getEmail();
        user.setEmail(email);
        user.setLogin(email.substring(0, email.indexOf('@')));
        return user;
    }
}
