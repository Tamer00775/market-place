package kz.halyk.finservice.test.MarketPlace.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * Information about role.
 */

@Entity
@Table(name ="role")
@ToString
public class Role {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    /**
     * Code of Role.
     */
    @Column(name = "code", unique = true, nullable = false)
    @Setter
    @Getter
    private String code;

    @Column(name = "deleted", columnDefinition = "boolean default false")
    @Setter
    @Getter
    private Boolean isDeleted = false;

}
