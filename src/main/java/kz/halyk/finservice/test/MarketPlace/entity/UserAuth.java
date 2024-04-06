package kz.halyk.finservice.test.MarketPlace.entity;

import kz.halyk.finservice.test.MarketPlace.enums.UserStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_auth")
public class UserAuth extends AbstractEntity{

    @Id
    @Column(name = "id")
    @Getter
    private Long id;

    @Column(name = "password_hash", nullable = false)
    private String password;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    @Getter
    @Setter
    private UserStatus status;

    @Column(name = "deleted", columnDefinition = "boolean default false")
    @Getter
    @Setter
    private Boolean isDeleted = false;

    @OneToOne
    @JoinColumn(name = "user_id")
    @Getter
    @Setter
    private User user;
}
