package lozm.entity.auth;

import lombok.Getter;
import lozm.entity.orders.Orders;
import lozm.object.code.AccountType;
import lozm.entity.BaseEntity;
import lozm.entity.coupon.CouponUser;
import lozm.object.vo.auth.AccountVo;
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


    public void insertAccount(AccountVo accountVo) {
        this.name = accountVo.getName();
        this.identifier = accountVo.getIdentifier();
        this.password = accountVo.getPassword();
        this.type = AccountType.valueOf(accountVo.getType());
        this.setBaseEntity(accountVo.getCreatedBy(), null, 1);
    }

    public void updateAccount(AccountVo accountVo) {
        this.name = accountVo.getName();
        this.identifier = accountVo.getIdentifier();
        if(!StringUtils.isEmpty(accountVo.getPassword())) {
            this.password = accountVo.getPassword();
        }
        this.type = StringUtils.isEmpty(accountVo.getType()) ? null : AccountType.valueOf(accountVo.getType());
        this.setBaseEntity(null, accountVo.getModifiedBy(), 1);
    }

    public void deleteAccount(AccountVo accountVo) {
        this.setBaseEntity(null, accountVo.getModifiedBy(), 0);
    }

}