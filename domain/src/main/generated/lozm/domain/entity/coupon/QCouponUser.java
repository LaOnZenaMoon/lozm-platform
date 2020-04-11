package lozm.domain.entity.coupon;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCouponUser is a Querydsl query type for CouponUser
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCouponUser extends EntityPathBase<CouponUser> {

    private static final long serialVersionUID = 228648608L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCouponUser couponUser = new QCouponUser("couponUser");

    public final lozm.domain.entity.QBaseEntity _super = new lozm.domain.entity.QBaseEntity(this);

    public final QCoupon coupon;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDt = _super.createdDt;

    //inherited
    public final NumberPath<Integer> flag = _super.flag;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDt = _super.modifiedDt;

    public final NumberPath<Long> quantity = createNumber("quantity", Long.class);

    public final lozm.domain.entity.user.QUser user;

    public QCouponUser(String variable) {
        this(CouponUser.class, forVariable(variable), INITS);
    }

    public QCouponUser(Path<? extends CouponUser> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCouponUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCouponUser(PathMetadata metadata, PathInits inits) {
        this(CouponUser.class, metadata, inits);
    }

    public QCouponUser(Class<? extends CouponUser> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.coupon = inits.isInitialized("coupon") ? new QCoupon(forProperty("coupon")) : null;
        this.user = inits.isInitialized("user") ? new lozm.domain.entity.user.QUser(forProperty("user")) : null;
    }

}

