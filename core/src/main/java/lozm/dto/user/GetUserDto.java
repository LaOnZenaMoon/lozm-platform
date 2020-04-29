package lozm.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lozm.code.UsersType;
import lozm.dto.item.GetItemDto;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class GetUserDto {

    private Long id;
    private String name;
    private String identifier;
    private String password;
    private UsersType type;

    public GetUserDto(Long id, String name, String identifier, String password, UsersType type) {
        this.id = id;
        this.name = name;
        this.identifier = identifier;
        this.password = password;
        this.type = type;
    }

    @Getter
    @Setter
    public static class Response {
        List<GetUserDto> list = new ArrayList<>();
    }

}
