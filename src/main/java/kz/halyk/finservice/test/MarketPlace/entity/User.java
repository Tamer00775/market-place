package kz.halyk.finservice.test.MarketPlace.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "_user")
@Getter
@Setter
@ToString
public class User extends AbstractEntity{

    /**
     * Id of user.
     */
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * firstname of user.
     */
    @Column(name = "first_name", nullable = false)
    private String firstName;

    /**
     * lastname of user.
     */
    @Column(name = "last_name", nullable = false)
    private String lastName;

    /**
     * middle name of user.
     */
    @Column(name = "middle_name")
    private String middleName;

    /**
     * login of user.
     */
    @Column(name = "login", nullable = false, unique = true)
    private String login;

    /**
     * email of user.
     */
    @Column(name = "email", nullable = false, unique = true)
    private String email;
}
