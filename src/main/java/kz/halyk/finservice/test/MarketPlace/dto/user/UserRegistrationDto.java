package kz.halyk.finservice.test.MarketPlace.dto.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class UserRegistrationDto {
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private String middleName;
    @Email
    @NotNull
    private String email;
    @NotNull
    // @Pattern(regexp = "")
    private String password;

}
