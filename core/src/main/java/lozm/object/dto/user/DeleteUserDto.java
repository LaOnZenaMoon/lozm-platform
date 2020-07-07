package lozm.object.dto.user;

import lombok.Getter;
import lozm.object.dto.BaseUserDto;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
public class DeleteUserDto {

    private Long id;
    private String name;
    private String identifier;
    private String password;
    private String type;
    private int flag;

    @Getter
    public static class Request extends BaseUserDto {
        @Size(min = 1)
        private List<DeleteUserDto> list = new ArrayList<>();
    }

}
