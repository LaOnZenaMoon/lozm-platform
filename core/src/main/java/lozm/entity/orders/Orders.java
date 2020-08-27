package lozm.entity.orders;

import lombok.Getter;
import lozm.entity.auth.Account;
import lozm.object.code.OrderStatus;
import lozm.entity.BaseEntity;
import lozm.entity.delivery.Delivery;
import lozm.object.vo.orders.OrdersVo;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(schema = "LOZM", name = "ORDERS")
@Getter
public class Orders extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "ORDERS_ID")
    private Long id;

    @Column(name = "ORDER_DT")
    private LocalDateTime orderDt;

    @Column(name = "STATUS")
    private OrderStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<OrdersItem> ordersItems;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "DELIVERY_ID")
    private Delivery delivery;

    public void insertOrders(OrdersVo ordersVo, Account account, Delivery delivery) {
        this.orderDt = LocalDateTime.now();
        this.status = OrderStatus.PREPARATION;
        this.account = account;
        this.delivery = delivery;
        this.setBaseEntity(ordersVo.getCreatedBy(), null, 1);
    }

    public void updateOrders(OrdersVo ordersVo) {
        this.status = OrderStatus.valueOf(ordersVo.getStatus());
        this.setBaseEntity(null,ordersVo.getModifiedBy(), 1);
    }

    public void deleteOrders(OrdersVo ordersVo) {
        this.setBaseEntity(null,ordersVo.getModifiedBy(), 0);
    }
}
