package lozm.domain.entity.coupon;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCoupon is a Querydsl query type for Coupon
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCoupon extends EntityPathBase<Coupon> {

    private static final long serialVersionUID = -1110824651L;

    public static final QCoupon coupon = new QCoupon("coupon");

    public final lozm.domain.entity.QBaseEntity _super = new lozm.domain.entity.QBaseEntity(this);

    public final NumberPath<Long> amount = createNumber("amount", Long.class);

    public final StringPath contents = createString("contents");

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDt = _super.createdDt;

    public final DateTimePath<java.time.LocalDateTime> endDt = createDateTime("endDt", java.time.LocalDateTime.class);

    //inherited
    public final NumberPath<Integer> flag = _super.flag;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDt = _super.modifiedDt;

    public final StringPath name = createString("name");

    public final NumberPath<Long> quantity = createNumber("quantity", Long.class);

    public final DateTimePath<java.time.LocalDateTime> startDt = createDateTime("startDt", java.time.LocalDateTime.class);

    public final EnumPath<lozm.core.code.CouponType> type = createEnum("type", lozm.core.code.CouponType.class);

    public QCoupon(String variable) {
        super(Coupon.class, forVariable(variable));
    }

    public QCoupon(Path<? extends Coupon> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCoupon(PathMetadata metadata) {
        super(Coupon.class, metadata);
    }

}

