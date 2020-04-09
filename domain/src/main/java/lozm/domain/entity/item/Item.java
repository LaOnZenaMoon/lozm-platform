package lozm.domain.entity.item;

import lombok.Getter;
import lozm.core.dto.item.PutItemDto;
import lozm.core.exception.APIException;
import lozm.core.vo.item.ItemVo;
import lozm.domain.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Getter
@Table(schema = "LOZM", name = "ITEM")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type")
public class Item extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private Long price;

    @Column(name = "QUANTITY")
    private Long quantity;

    public void insertItem(ItemVo itemVo) {
        this.name = itemVo.getName();
        this.price = itemVo.getPrice();
        this.quantity = itemVo.getQuantity();
    }

    public void updateItem(PutItemDto.Request reqDto) {
        this.name = reqDto.getName();
        this.price = reqDto.getPrice();
        this.quantity = reqDto.getQuantity();
        this.setBaseEntity("", reqDto.getFlag());
    }

    public void decreaseItemQuantity(Long quantity) throws Exception {
        this.quantity -= quantity;
        if(this.quantity < 0) throw new APIException("ORDERS_SAVE_ITEM_NO_QUANTITY", "Item quantity is insufficient.");
    }

}