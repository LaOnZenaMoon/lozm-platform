package lozm.object.dto.store;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetStoreDto {

    private Long id;
    private String name;
    private String telNumber;
    private String info;


    @Getter
    @Setter
    public static class Response {
        List<GetStoreDto> list = new ArrayList<>();
    }

}
