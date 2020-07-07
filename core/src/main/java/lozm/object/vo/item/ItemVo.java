package lozm.object.vo.item;

import lombok.Builder;
import lombok.Getter;
import lozm.object.vo.BaseVo;

import java.time.LocalDateTime;

@Getter
public class ItemVo extends BaseVo {

    private Long id;
    private String name;
    private Long price;
    private Long quantity;
    private String type;
    private String contents;
    private String size;
    private int flag;
    private Long storeId;


    @Builder
    public ItemVo(LocalDateTime createdDt, LocalDateTime modifiedDt, Long createdBy, Long modifiedBy, int flag, Long id, String name, Long price, Long quantity, String type, String contents, String size, int flag1, Long storeId) {
        super(createdDt, modifiedDt, createdBy, modifiedBy, flag);
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.type = type;
        this.contents = contents;
        this.size = size;
        this.flag = flag1;
        this.storeId = storeId;
    }

}
