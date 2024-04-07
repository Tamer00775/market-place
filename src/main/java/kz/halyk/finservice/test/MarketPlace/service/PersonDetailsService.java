package kz.halyk.finservice.test.MarketPlace.service;

import kz.halyk.finservice.test.MarketPlace.entity.Role;
import kz.halyk.finservice.test.MarketPlace.entity.User;
import kz.halyk.finservice.test.MarketPlace.entity.UserRole;
import kz.halyk.finservice.test.MarketPlace.security.PersonDetails;
import kz.halyk.finservice.test.MarketPlace.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonDetailsService implements UserDetailsService {

    private final UserServiceImpl userService;
    private final UserRoleService userRoleService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> byLogin = userService.findByLogin(username);
        if (byLogin.isEmpty()) {
            throw new UsernameNotFoundException("User with login " + username + "not found");
        }
        log.info("User {}", username);
        List<UserRole> userRolesByUser = userRoleService.findUserRolesByUser(byLogin.get());
        List<Role> roles = userRolesByUser.stream().map(UserRole::getRole).collect(Collectors.toList());
        List<String> roleCodes = roles.stream().map(Role::getCode).collect(Collectors.toList());
        return new PersonDetails(byLogin.get(), roleCodes);
    }
}
