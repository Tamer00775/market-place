package kz.halyk.finservice.test.MarketPlace.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String lastName;
    private String firstName;
    private String middleName;
    private String login;
    private String email;
}
