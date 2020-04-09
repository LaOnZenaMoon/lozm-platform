package lozm.core.dto.item;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GetClothingDto {

    private Long id;
    private String name;
    private Long price;
    private Long quantity;
    private String contents;
    private String size;

    public GetClothingDto(Long id, String name, Long price, Long quantity, String contents, String size) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.contents = contents;
        this.size = size;
    }

    @Getter
    @Setter
    public static class Response {
        List<GetClothingDto> list = new ArrayList<>();
    }
}