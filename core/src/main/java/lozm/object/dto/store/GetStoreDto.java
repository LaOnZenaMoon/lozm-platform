package lozm.object.dto.store;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class GetStoreDto {

    private Long id;
    private String name;
    private String telNumber;
    private String info;

    public GetStoreDto(Long id, String name, String telNumber, String info) {
        this.id = id;
        this.name = name;
        this.telNumber = telNumber;
        this.info = info;
    }

    @Getter
    @Setter
    public static class Response {
        List<GetStoreDto> list = new ArrayList<>();
    }

}
