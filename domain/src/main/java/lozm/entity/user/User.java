package lozm.entity.user;

import lombok.Getter;
import lozm.code.UserType;
import lozm.vo.user.UserVo;
import lozm.entity.BaseEntity;
import lozm.entity.coupon.CouponUser;
import lozm.entity.orders.Orders;

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

    @OneToMany(mappedBy = "user")
    private List<CouponUser> couponUsers;


    public void insertUser(UserVo userVo) {
        this.name = userVo.getName();
        this.identifier = userVo.getIdentifier();
        this.password = userVo.getPassword();
        this.type = UserType.valueOf(userVo.getType());
    }

    public void updateUser(UserVo userVo) {
        this.name = userVo.getName();
        this.password = userVo.getPassword();
        this.setBaseEntity("", userVo.getFlag());
    }

}