package lozm.domain.entity.orders;

import lombok.Getter;
import lozm.domain.entity.BaseEntity;
import lozm.domain.entity.item.Item;

import javax.persistence.*;

@Entity
@Getter
@Table(schema = "LOZM", name = "ORDERS_ITEM")
public class OrdersItem extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "ORDERS_ITEM_ID")
    private Long id;

    @Column(name = "ORDERED_PRICE")
    private Long orderedPrice;

    @Column(name = "QUANTITY")
    private Long quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDERS_ID")
    private Orders orders;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    private Item item;


    public void insertOrdersItem(Long orderedPrice, Long quantity, Orders orders, Item item) {
        this.orderedPrice = orderedPrice;
        this.quantity = quantity;
        this.orders = orders;
        this.item = item;
    }

}
