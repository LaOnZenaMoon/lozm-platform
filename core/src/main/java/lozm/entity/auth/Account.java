package lozm.entity.auth;

import lombok.Getter;
import lozm.entity.orders.Orders;
import lozm.object.code.AccountType;
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
    private AccountType type;

    @OneToMany(mappedBy = "account")
    private List<Orders> orders;

    @OneToMany(mappedBy = "account")
    private List<CouponUser> couponUsers;

    @OneToMany(mappedBy = "account")
    private List<AccountRole> accountRoles;


    public void insertUser(UserVo userVo) {
        this.name = userVo.getName();
        this.identifier = userVo.getIdentifier();
        this.password = userVo.getPassword();
        this.type = AccountType.valueOf(userVo.getType());
        this.setBaseEntity(userVo.getCreatedBy(), null, 1);
    }

    public void updateUser(UserVo userVo) {
        this.name = userVo.getName();
        this.identifier = userVo.getIdentifier();
        if(!StringUtils.isEmpty(userVo.getPassword())) {
            this.password = userVo.getPassword();
        }
        this.type = StringUtils.isEmpty(userVo.getType()) ? null : AccountType.valueOf(userVo.getType());
        this.setBaseEntity(null, userVo.getModifiedBy(), 1);
    }

    public void deleteUser(UserVo userVo) {
        this.setBaseEntity(null, userVo.getModifiedBy(), 0);
    }

}