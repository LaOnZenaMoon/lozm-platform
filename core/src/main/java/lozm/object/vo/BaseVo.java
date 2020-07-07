package lozm.object.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class BaseVo {

    private LocalDateTime createdDt;
    private LocalDateTime modifiedDt;
    private Long createdBy;
    private Long modifiedBy;
    private int flag = 1;

}
