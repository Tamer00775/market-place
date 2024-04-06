package kz.halyk.finservice.test.MarketPlace.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_role")
public class UserRole extends AbstractEntity{

    @Id
    @Column(name = "id")
    @Getter
    @Setter
    private Long id;

    /***
     * User
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    @Getter
    @Setter
    private User user;

    /**
     * Role
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id")
    @Getter
    @Setter
    private Role role;

    @Column(name = "active")
    @Getter
    @Setter
    private Boolean isActive;

    @Column(name = "deleted", columnDefinition = "boolean default false")
    @Getter
    @Setter
    private Boolean isDeleted = false;

}
