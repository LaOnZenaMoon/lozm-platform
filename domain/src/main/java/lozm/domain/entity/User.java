package lozm.domain.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(schema = "LOZM", name = "USER")
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

}