package kz.halyk.finservice.test.MarketPlace.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "user_auth")
public class UserAuth {

    @Id
    @Column(name = "id")
    @Getter
    private Long id;

    @Column(name = "password_hash", nullable = false)
    private String password;

    @Column(name = "status")
    private String status;

    @Column(name = "deleted", columnDefinition = "boolean default false")
    private Boolean isDeleted = false;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
