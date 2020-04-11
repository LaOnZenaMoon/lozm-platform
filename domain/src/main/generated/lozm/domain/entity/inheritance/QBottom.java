package lozm.domain.entity.inheritance;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBottom is a Querydsl query type for Bottom
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBottom extends EntityPathBase<Bottom> {

    private static final long serialVersionUID = -1695512024L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBottom bottom = new QBottom("bottom");

    public final lozm.domain.entity.item.QItem _super = new lozm.domain.entity.item.QItem(this);

    public final lozm.domain.entity.embedded.QClothing clothing;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDt = _super.createdDt;

    //inherited
    public final NumberPath<Integer> flag = _super.flag;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDt = _super.modifiedDt;

    //inherited
    public final StringPath name = _super.name;

    //inherited
    public final NumberPath<Long> price = _super.price;

    //inherited
    public final NumberPath<Long> quantity = _super.quantity;

    //inherited
    public final StringPath type = _super.type;

    public QBottom(String variable) {
        this(Bottom.class, forVariable(variable), INITS);
    }

    public QBottom(Path<? extends Bottom> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBottom(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBottom(PathMetadata metadata, PathInits inits) {
        this(Bottom.class, metadata, inits);
    }

    public QBottom(Class<? extends Bottom> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.clothing = inits.isInitialized("clothing") ? new lozm.domain.entity.embedded.QClothing(forProperty("clothing")) : null;
    }

}

