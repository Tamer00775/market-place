package kz.halyk.finservice.test.MarketPlace.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ApiModel("DTO for authorization")
public class AuthDto {
    @ApiModelProperty("Login for authorization")
    @NotNull
    private String login;
    @ApiModelProperty("Password for authorization")
    @NotNull
    private String password;
}
