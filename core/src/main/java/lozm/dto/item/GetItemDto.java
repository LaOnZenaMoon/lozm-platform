package lozm.dto.item;

import lombok.Getter;
import lombok.Setter;
import lozm.entity.embedded.Clothing;
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
    private Clothing clothing;
    private Store store;


    public GetItemDto(Long id, String name, Long price, Long quantity, String type, Store store) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.type = type;
        this.store = store;
    }

    public GetItemDto(Long id, String name, Long price, Long quantity, String type, Clothing clothing, Store store) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.type = type;
        this.clothing = clothing;
        this.store = store;
    }

    @Getter
    @Setter
    public static class Response {
        List<GetItemDto> list = new ArrayList<>();
    }

}
