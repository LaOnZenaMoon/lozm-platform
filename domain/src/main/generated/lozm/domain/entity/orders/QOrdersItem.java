package lozm.domain.entity.orders;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrdersItem is a Querydsl query type for OrdersItem
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOrdersItem extends EntityPathBase<OrdersItem> {

    private static final long serialVersionUID = -906354424L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrdersItem ordersItem = new QOrdersItem("ordersItem");

    public final lozm.domain.entity.QBaseEntity _super = new lozm.domain.entity.QBaseEntity(this);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDt = _super.createdDt;

    //inherited
    public final NumberPath<Integer> flag = _super.flag;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final lozm.domain.entity.item.QItem item;

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDt = _super.modifiedDt;

    public final NumberPath<Long> orderedPrice = createNumber("orderedPrice", Long.class);

    public final QOrders orders;

    public final NumberPath<Long> quantity = createNumber("quantity", Long.class);

    public QOrdersItem(String variable) {
        this(OrdersItem.class, forVariable(variable), INITS);
    }

    public QOrdersItem(Path<? extends OrdersItem> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrdersItem(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrdersItem(PathMetadata metadata, PathInits inits) {
        this(OrdersItem.class, metadata, inits);
    }

    public QOrdersItem(Class<? extends OrdersItem> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.item = inits.isInitialized("item") ? new lozm.domain.entity.item.QItem(forProperty("item")) : null;
        this.orders = inits.isInitialized("orders") ? new QOrders(forProperty("orders"), inits.get("orders")) : null;
    }

}

