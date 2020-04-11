package lozm.domain.entity.embedded;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QClothing is a Querydsl query type for Clothing
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QClothing extends BeanPath<Clothing> {

    private static final long serialVersionUID = -1217280717L;

    public static final QClothing clothing = new QClothing("clothing");

    public final StringPath contents = createString("contents");

    public final StringPath sizes = createString("sizes");

    public QClothing(String variable) {
        super(Clothing.class, forVariable(variable));
    }

    public QClothing(Path<? extends Clothing> path) {
        super(path.getType(), path.getMetadata());
    }

    public QClothing(PathMetadata metadata) {
        super(Clothing.class, metadata);
    }

}

