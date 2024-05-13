package kz.halyk.finservice.test.MarketPlace.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "_user")
@Getter
@Setter
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

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    /**
     * email of user.
     */
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @OneToOne(mappedBy = "user")
    private UserAuth userAuth;

    @OneToMany(mappedBy = "user")
    private List<UserRole> userRoleList;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_subscribed_products",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> subscribedProducts = new HashSet<>();

}
