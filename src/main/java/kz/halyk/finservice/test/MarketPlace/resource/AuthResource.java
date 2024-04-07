package kz.halyk.finservice.test.MarketPlace.resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api("REST about authorization in application")
public class AuthResource {

    private final AuthService authService;

    @PostMapping("/login")
    @ApiOperation("Function to login in app")
    public ResponseEntity<Map<String, String>> login(@RequestBody AuthDto authDto) {
        return ResponseEntity.ok(authService.login(authDto));
    }

    @PostMapping("/register")
    @ApiOperation("Function to register as user in app")
    public ResponseEntity<UserDto> register(@RequestBody @Valid UserRegistrationDto userRegistrationDto) {
        return ResponseEntity.ok(authService.register(userRegistrationDto));
    }
}
