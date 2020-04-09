package lozm.core.dto.item;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GetItemDto {

    private Long id;
    private String name;
    private Long price;
    private Long quantity;

    public GetItemDto(Long id, String name, Long price, Long quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    @Getter
    @Setter
    public static class Response {
        List<GetItemDto> list = new ArrayList<>();
    }

}
