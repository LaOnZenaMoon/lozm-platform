package lozm.domain.entity.orders;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrders is a Querydsl query type for Orders
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOrders extends EntityPathBase<Orders> {

    private static final long serialVersionUID = 2038129749L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrders orders = new QOrders("orders");

    public final lozm.domain.entity.QBaseEntity _super = new lozm.domain.entity.QBaseEntity(this);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDt = _super.createdDt;

    public final lozm.domain.entity.delivery.QDelivery delivery;

    //inherited
    public final NumberPath<Integer> flag = _super.flag;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDt = _super.modifiedDt;

    public final DateTimePath<java.time.LocalDateTime> orderDt = createDateTime("orderDt", java.time.LocalDateTime.class);

    public final ListPath<OrdersItem, QOrdersItem> ordersItems = this.<OrdersItem, QOrdersItem>createList("ordersItems", OrdersItem.class, QOrdersItem.class, PathInits.DIRECT2);

    public final EnumPath<lozm.core.code.OrderStatus> status = createEnum("status", lozm.core.code.OrderStatus.class);

    public final lozm.domain.entity.user.QUser user;

    public QOrders(String variable) {
        this(Orders.class, forVariable(variable), INITS);
    }

    public QOrders(Path<? extends Orders> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrders(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrders(PathMetadata metadata, PathInits inits) {
        this(Orders.class, metadata, inits);
    }

    public QOrders(Class<? extends Orders> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.delivery = inits.isInitialized("delivery") ? new lozm.domain.entity.delivery.QDelivery(forProperty("delivery"), inits.get("delivery")) : null;
        this.user = inits.isInitialized("user") ? new lozm.domain.entity.user.QUser(forProperty("user")) : null;
    }

}

