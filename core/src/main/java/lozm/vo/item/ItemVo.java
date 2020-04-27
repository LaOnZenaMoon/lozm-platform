package lozm.vo.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemVo {

    private Long id;
    private String name;
    private Long price;
    private Long quantity;
    private String type;
    private String contents;
    private String size;
    private int flag;
    private Long storeId;

}
