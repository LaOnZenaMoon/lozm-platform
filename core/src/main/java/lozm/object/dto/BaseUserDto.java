package lozm.object.dto;

import lombok.Getter;
import lozm.object.code.UsersType;
import org.springframework.util.ObjectUtils;

@Getter
public class BaseUserDto {

    private Long createdBy;
    private Long modifiedBy;


    public void setCreatedBy(Long id) {
        this.createdBy = ObjectUtils.isEmpty(id) ? Long.valueOf(UsersType.API_SYSTEM.toString()) : id;
    }

    public void setModifiedBy(Long id) {
        this.createdBy = ObjectUtils.isEmpty(id) ? Long.valueOf(UsersType.API_SYSTEM.toString()) : id;
    }

}
