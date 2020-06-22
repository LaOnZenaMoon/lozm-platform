package lozm.object.dto;

import lozm.object.code.UsersType;
import org.springframework.util.StringUtils;


public abstract class BaseUserDto {

    private String createdBy;
    private String modifiedBy;

    public void setCreatedBy(String id) {
        this.createdBy = StringUtils.isEmpty(id) ? UsersType.API_SYSTEM.toString() : id;
    }

    public void setModifiedBy(String id) {
        this.createdBy = StringUtils.isEmpty(id) ? UsersType.API_SYSTEM.toString() : id;
    }

}
