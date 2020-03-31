package lozm.domain.entity;

import lombok.Getter;
import lozm.core.dto.user.PostUserDto;
import lozm.core.dto.user.PutUserDto;
import lozm.core.code.UserType;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "user")
    private List<Orders> orders;


    public void insertUser(PostUserDto.Request postUserDto) {
        this.name = postUserDto.getName();
        this.identifier = postUserDto.getIdentifier();
        this.password = postUserDto.getPassword();
        this.type = UserType.valueOf(postUserDto.getType());
    }

    public void updateUser(PutUserDto.Request putUserDto) {
        this.name = putUserDto.getName();
        this.password = putUserDto.getPassword();
        this.setBaseEntity("", 1);
    }

}