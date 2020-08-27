package lozm.entity.auth;

import lombok.Getter;
import lozm.entity.orders.Orders;
import lozm.object.code.UsersType;
import lozm.entity.BaseEntity;
import lozm.entity.coupon.CouponUser;
import lozm.object.vo.user.UserVo;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.List;



@Entity
@Table(schema = "LOZM", name = "ACCOUNT")
@Getter
public class Account extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "ACCOUNT_ID")
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


//    @ManyToMany(fetch = FetchType.LAZY, cascade={CascadeType.ALL})
//    @JoinTable(name = "account_roles", joinColumns = { @JoinColumn(name = "account_id") }, inverseJoinColumns = {
//            @JoinColumn(name = "role_id") })
//    private Set<Role> userRoles = new HashSet<>();


    public void insertUser(UserVo userVo) {
        this.name = userVo.getName();
        this.identifier = userVo.getIdentifier();
        this.password = userVo.getPassword();
        this.type = UsersType.valueOf(userVo.getType());
        this.setBaseEntity(userVo.getCreatedBy(), null, 1);
    }

    public void updateUser(UserVo userVo) {
        this.name = userVo.getName();
        this.identifier = userVo.getIdentifier();
        if(!StringUtils.isEmpty(userVo.getPassword())) {
            this.password = userVo.getPassword();
        }
        this.type = StringUtils.isEmpty(userVo.getType()) ? null : UsersType.valueOf(userVo.getType());
        this.setBaseEntity(null, userVo.getModifiedBy(), 1);
    }

    public void deleteUser(UserVo userVo) {
        this.setBaseEntity(null, userVo.getModifiedBy(), 0);
    }

}