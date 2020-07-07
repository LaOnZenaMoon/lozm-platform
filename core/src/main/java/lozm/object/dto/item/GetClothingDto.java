package lozm.object.dto.item;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetClothingDto {

    private Long id;
    private String name;
    private Long price;
    private Long quantity;
    private String contents;
    private String size;


    @Getter
    @Setter
    public static class Response {
        List<GetClothingDto> list = new ArrayList<>();
    }
}