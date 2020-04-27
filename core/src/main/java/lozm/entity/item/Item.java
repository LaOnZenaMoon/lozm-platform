package lozm.entity.item;

import lombok.Getter;
import lozm.entity.BaseEntity;
import lozm.entity.store.Store;
import lozm.exception.APIException;
import lozm.vo.item.ItemVo;

import javax.persistence.*;

@Entity
@Table(schema = "LOZM", name = "ITEM")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type")
@Getter
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

    @Column(name = "type", insertable = false, updatable = false)
    protected String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STORE_ID")
    private Store store;

    public void insertItem(ItemVo itemVo) {
        this.name = itemVo.getName();
        this.price = itemVo.getPrice();
        this.quantity = itemVo.getQuantity();
    }

    public void updateItem(ItemVo itemVo) {
        this.name = itemVo.getName();
        this.price = itemVo.getPrice();
        this.quantity = itemVo.getQuantity();
        this.setBaseEntity("", itemVo.getFlag());
    }

    public void decreaseItemQuantity(Long quantity) throws Exception {
        this.quantity -= quantity;
        if(this.quantity < 0) throw new APIException("ORDERS_SAVE_ITEM_NO_QUANTITY", "Item quantity is insufficient.");
    }

}