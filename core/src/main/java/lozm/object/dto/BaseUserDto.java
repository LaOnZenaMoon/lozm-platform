package lozm.object.dto;

import lombok.Getter;
import lozm.object.code.UsersType;
import org.springframework.util.ObjectUtils;

@Getter
public class BaseUserDto {

    private Long createdBy;
    private Long modifiedBy;


    public void setCreatedBy(Long id) {
        this.createdBy = ObjectUtils.isEmpty(id) ? -1L : id;
    }

    public void setModifiedBy(Long id) {
        this.modifiedBy = ObjectUtils.isEmpty(id) ? -1L : id;
    }

}
