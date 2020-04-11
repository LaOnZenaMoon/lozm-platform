package lozm.core.vo.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {

    private Long id;
    private String name;
    private String identifier;
    private String password;
    private String type;
    private int flag;

}
