package kz.halyk.finservice.test.MarketPlace.resource;

import kz.halyk.finservice.test.MarketPlace.entity.User;
import kz.halyk.finservice.test.MarketPlace.service.UserService;
import kz.halyk.finservice.test.MarketPlace.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/user/current")
@RequiredArgsConstructor
@RestController
public class UserResource {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<User> getCurrentUser() {
        return ResponseEntity.ok(SecurityUtils.getCurrentPerson());
    }
}
