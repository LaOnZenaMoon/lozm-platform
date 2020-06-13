package lozm.entity.user;

import lombok.Getter;
import lozm.object.code.UsersType;
import lozm.entity.BaseEntity;
import lozm.entity.coupon.CouponUser;
import lozm.entity.orders.Orders;
import lozm.object.vo.user.UserVo;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.List;



@Entity
@Table(schema = "LOZM", name = "USERS")
@Getter
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
    private UsersType type;

    @OneToMany(mappedBy = "user")
    private List<Orders> orders;

    @OneToMany(mappedBy = "user")
    private List<CouponUser> couponUsers;


    public void insertUser(UserVo userVo) {
        this.name = userVo.getName();
        this.identifier = userVo.getIdentifier();
        this.password = userVo.getPassword();
        this.type = UsersType.valueOf(userVo.getType());
    }

    public void updateUser(UserVo userVo) {
        this.name = userVo.getName();
        this.identifier = userVo.getIdentifier();
        this.password = userVo.getPassword();
        this.type = StringUtils.isEmpty(userVo.getType()) ? null : UsersType.valueOf(userVo.getType());
        this.setBaseEntity("", userVo.getFlag());
    }

    public void deleteUser(UserVo userVo) {
        this.setBaseEntity("", userVo.getFlag());
    }

}