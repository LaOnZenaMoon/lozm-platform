package lozm.core.dto.user;

import lombok.Getter;
import lombok.Setter;
import lozm.core.code.UserType;
import lozm.core.dto.item.GetItemDto;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GetUserDto {

    private Long id;
    private String name;
    private String identifier;
    private UserType type;

    public GetUserDto(Long id, String name, String identifier, UserType type) {
        this.id = id;
        this.name = name;
        this.identifier = identifier;
        this.type = type;
    }

    @Getter
    @Setter
    public static class Response {
        List<GetItemDto> list = new ArrayList<>();
    }

}
