package lozm.entity;

import lozm.object.code.UsersType;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Column(name = "CREATED_DT", updatable = false)
    private LocalDateTime createdDt = LocalDateTime.now();

    @Column(name = "MODIFIED_DT")
    private LocalDateTime modifiedDt;

    @Column(name = "CREATED_BY", updatable = false, nullable = false)
    private String createdBy = UsersType.API_SYSTEM.toString();

    @Column(name = "MODIFY_BY", nullable = false)
    private String modifiedBy;

    @Column(name = "FLAG")
    private int flag = 1;

    public void setBaseEntity(String modifiedBy, int flag) {
        this.modifiedDt = LocalDateTime.now();
        this.modifiedBy = StringUtils.isEmpty(modifiedBy) ? UsersType.API_SYSTEM.toString() : modifiedBy;
        if(!StringUtils.isEmpty(flag)) this.flag = flag;
    }

}
