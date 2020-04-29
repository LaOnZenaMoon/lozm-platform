package lozm.dto.item;

import lombok.Getter;
import lombok.Setter;
import lozm.entity.embedded.Clothing;
import lozm.entity.store.Store;

import javax.persistence.Column;
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
    private Long storeId;
    private String storeName;


    public GetItemDto(Long id, String name, Long price, Long quantity, String type, Long storeId, String storeName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.type = type;
        this.storeId = storeId;
        this.storeName = storeName;
    }

    public GetItemDto(Long id, String name, Long price, Long quantity, String type, String contents, String sizes, Long storeId, String storeName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.type = type;
        this.contents = contents;
        this.sizes = sizes;
        this.storeId = storeId;
        this.storeName = storeName;
    }

    @Getter
    @Setter
    public static class Response {
        List<GetItemDto> list = new ArrayList<>();
    }

}
