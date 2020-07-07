package lozm.object.vo.user;

import lombok.Builder;
import lombok.Getter;
import lozm.object.vo.BaseVo;

import java.time.LocalDateTime;

@Getter
public class UserVo extends BaseVo {

    private Long id;
    private String name;
    private String identifier;
    private String password;
    private String type;
    private int flag;


    @Builder
    public UserVo(LocalDateTime createdDt, LocalDateTime modifiedDt, Long createdBy, Long modifiedBy, int flag, Long id, String name, String identifier, String password, String type, int flag1) {
        super(createdDt, modifiedDt, createdBy, modifiedBy, flag);
        this.id = id;
        this.name = name;
        this.identifier = identifier;
        this.password = password;
        this.type = type;
        this.flag = flag1;
    }

}
