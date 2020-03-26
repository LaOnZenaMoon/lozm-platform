package lozm.domain.entity;

import lombok.Getter;

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

    @Column(name = "COUNT")
    private int count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDERS_ID")
    private Orders orders;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    private Item item;

}
