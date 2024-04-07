package kz.halyk.finservice.test.MarketPlace.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("DTO for User")
public class UserDto {
    @ApiModelProperty("Id of user")
    private Long id;
    @ApiModelProperty("Lastname of user")
    private String lastName;
    @ApiModelProperty("Firstname of user")
    private String firstName;
    @ApiModelProperty("Middlename of user")
    private String middleName;
    @ApiModelProperty("Login of user")
    private String login;
    @ApiModelProperty("Email of user")
    private String email;
}
