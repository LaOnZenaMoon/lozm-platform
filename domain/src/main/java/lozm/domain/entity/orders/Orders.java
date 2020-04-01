package lozm.domain.entity.orders;

import lombok.Getter;
import lozm.core.code.OrderStatus;
import lozm.core.dto.orders.PostOrdersDto;
import lozm.core.dto.orders.PutOrdersDto;
import lozm.domain.entity.BaseEntity;
import lozm.domain.entity.delivery.Delivery;
import lozm.domain.entity.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Table(schema = "LOZM", name = "ORDERS")
public class Orders extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "ORDERS_ID")
    private Long id;

    @Column(name = "ORDER_DT")
    private LocalDateTime orderDt;

    @Column(name = "STATUS")
    private OrderStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<OrdersItem> ordersItems;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "DELIVERY_ID")
    private Delivery delivery;

    public void insertOrders(PostOrdersDto.Request reqDto, User user) {
        this.orderDt = LocalDateTime.now();
        this.status = OrderStatus.PREPARATION;
        this.user = user;
    }

    public void updateOrders(PutOrdersDto.Request reqDto) {
        this.status = reqDto.getStatus();
        this.setBaseEntity("", reqDto.getFlag());
    }
}
