package lozm.domain.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public class BaseEntity {

    @Column(name = "CREATED_DT")
    private LocalDateTime createdDt;

    @Column(name = "MODIFIED_DT")
    private LocalDateTime modifiedDt;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "MODIFY_BY")
    private String modifiedBy;

    @Column(name = "FLAG")
    private int flag;

}
