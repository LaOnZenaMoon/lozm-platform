package lozm.domain.entity.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 1073507797L;

    public static final QUser user = new QUser("user");

    public final lozm.domain.entity.QBaseEntity _super = new lozm.domain.entity.QBaseEntity(this);

    public final ListPath<lozm.domain.entity.coupon.CouponUser, lozm.domain.entity.coupon.QCouponUser> couponUsers = this.<lozm.domain.entity.coupon.CouponUser, lozm.domain.entity.coupon.QCouponUser>createList("couponUsers", lozm.domain.entity.coupon.CouponUser.class, lozm.domain.entity.coupon.QCouponUser.class, PathInits.DIRECT2);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDt = _super.createdDt;

    //inherited
    public final NumberPath<Integer> flag = _super.flag;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath identifier = createString("identifier");

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDt = _super.modifiedDt;

    public final StringPath name = createString("name");

    public final ListPath<lozm.domain.entity.orders.Orders, lozm.domain.entity.orders.QOrders> orders = this.<lozm.domain.entity.orders.Orders, lozm.domain.entity.orders.QOrders>createList("orders", lozm.domain.entity.orders.Orders.class, lozm.domain.entity.orders.QOrders.class, PathInits.DIRECT2);

    public final StringPath password = createString("password");

    public final EnumPath<lozm.core.code.UserType> type = createEnum("type", lozm.core.code.UserType.class);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

