package kz.halyk.finservice.test.MarketPlace.security;

import kz.halyk.finservice.test.MarketPlace.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class PersonDetails implements UserDetails {

    private final User user;
    private final List<String> roleCodes;

    @Override
    @Transactional(readOnly = true)
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        log.info("roleCodes : {}", roleCodes);
        roleCodes.forEach(role -> {
            grantedAuthorities.add(new SimpleGrantedAuthority(role));
        });
        log.info("GrantAuthorities: {}", grantedAuthorities);

        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return user.getUserAuth().getPassword();
    }

    @Override
    public String getUsername() {
        return user.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
