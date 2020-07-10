package lozm.entity;

import lombok.Getter;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Column(name = "CREATED_DT", updatable = false)
    private LocalDateTime createdDt;

    @Column(name = "MODIFIED_DT")
    private LocalDateTime modifiedDt;

    @Column(name = "CREATED_BY", updatable = false, nullable = false)
    private Long createdBy = -1L;

    @Column(name = "MODIFY_BY")
    private Long modifiedBy = -1L;

    @Column(name = "FLAG")
    private int flag = 1;

    public void setBaseEntity(Long createdBy, Long modifiedBy, int flag) {
        this.createdDt = LocalDateTime.now();
        this.createdBy = ObjectUtils.isEmpty(createdBy) ? -1L : createdBy;
        this.modifiedDt = LocalDateTime.now();
        this.modifiedBy = ObjectUtils.isEmpty(modifiedBy) ? -1L : modifiedBy;
        if(!StringUtils.isEmpty(flag)) this.flag = flag;
    }

}
