package lozm.domain.entity;

import lombok.Getter;
import lozm.core.dto.PostUserDto;
import lozm.core.dto.PutUserDto;
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

    public void insertUser(PostUserDto.Request postUserDto) {
        this.name = postUserDto.getName();
        this.identifier = postUserDto.getIdentifier();
        this.password = postUserDto.getPassword();
    }

    public void updateUser(PutUserDto.Request putUserDto) {
        this.name = putUserDto.getName();
        this.password = putUserDto.getPassword();
    }

}