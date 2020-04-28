package lozm.dto.item;

import lombok.Getter;
import lombok.Setter;
import lozm.entity.store.Store;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GetItemDto {

    private Long id;
    private String name;
    private Long price;
    private Long quantity;
    protected String type;
    private String contents;
    private String sizes;
    private Store store;


    public GetItemDto(Long id, String name, Long price, Long quantity, String contents, String sizes, Store store) {
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
