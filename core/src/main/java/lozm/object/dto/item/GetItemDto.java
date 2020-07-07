package lozm.object.dto.item;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetItemDto {

    private Long id;
    private String name;
    private Long price;
    private Long quantity;
    protected String type;
    private String contents;
    private String sizes;
    private Long storeId;
    private String storeName;


    @Getter
    @Setter
    public static class Response {
        List<GetItemDto> list = new ArrayList<>();
    }

}
