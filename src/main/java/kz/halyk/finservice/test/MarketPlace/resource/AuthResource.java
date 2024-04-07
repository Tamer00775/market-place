package kz.halyk.finservice.test.MarketPlace.resource;

import kz.halyk.finservice.test.MarketPlace.dto.user.AuthDto;
import kz.halyk.finservice.test.MarketPlace.dto.user.UserDto;
import kz.halyk.finservice.test.MarketPlace.dto.user.UserRegistrationDto;
import kz.halyk.finservice.test.MarketPlace.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/open-api")
@Validated
public class AuthResource {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody AuthDto authDto) {
        return ResponseEntity.ok(authService.login(authDto));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody @Valid UserRegistrationDto userRegistrationDto) {
        return ResponseEntity.ok(authService.register(userRegistrationDto));
    }
}
