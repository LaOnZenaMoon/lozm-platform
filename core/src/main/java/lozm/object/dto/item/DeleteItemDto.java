package lozm.object.dto.item;

import lombok.Getter;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
public class DeleteItemDto {

    private Long id;
    private String name;
    private Long price;
    private Long quantity;
    private String type;
    private String contents;
    private String size;
    private int flag;
    private Long storeId;

    @Getter
    public static class Request {
        @Size(min = 1)
        private List<DeleteItemDto> list = new ArrayList<>();
    }

}
