package lozm.domain.entity;

import lombok.Getter;
import lozm.domain.code.UserType;

import javax.persistence.*;

@Entity
@Getter
@Table(schema = "LOZM", name = "USERS")
public class User extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "IDENTIFIER")
    private String identifier;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "TYPE")
    private UserType type;

}