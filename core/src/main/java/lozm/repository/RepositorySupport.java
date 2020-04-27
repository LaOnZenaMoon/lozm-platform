package lozm.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lozm.code.ItemType;
import lozm.entity.inheritance.Outer;
import lozm.entity.user.User;
import lozm.exception.APIException;
import lozm.vo.item.ItemVo;
import lozm.vo.sign.SignVo;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static lozm.entity.inheritance.QBottom.bottom;
import static lozm.entity.inheritance.QOuter.outer;
import static lozm.entity.inheritance.QShoes.shoes;
import static lozm.entity.inheritance.QTop.top;
import static lozm.entity.user.QUser.user;
//import static lozm.entity.store.QStore.store;


@Repository
public class RepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    public RepositorySupport(JPAQueryFactory jpaQueryFactory) {
        super(User.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }


    public List<User> selectUserDetail(SignVo signVo) throws Exception {
        return jpaQueryFactory
                .select(Projections.fields(
                    User.class,
                        user.id,
                        user.name,
                        user.identifier,
                        user.type
                ))
                .from(user)
                .where(
                        user.identifier.eq(signVo.getIdentifier())
                        .and(user.password.eq(signVo.getPassword()))
                        .and(user.flag.eq(1))
                )
                .fetch();
    }



    public List<Outer> selectOuterList(ItemVo itemVo) throws Exception {
        return jpaQueryFactory
                .select(Projections.fields(
                        Outer.class,
                        outer.id,
                        outer.name,
                        outer.price,
                        outer.quantity,
                        outer.type,
                        outer.clothing.contents,
                        outer.clothing.sizes
                ))
                .from(outer)
                .where(
                        outer.flag.eq(1)
                        .and(outer.type.eq(itemVo.getType()))
                        .and(checkStoreId(itemVo))
                )
                .fetch();
    }

    private BooleanExpression checkStoreId(ItemVo itemVo) {
        if(StringUtils.isEmpty(itemVo.getStoreId())) return null;

        BooleanExpression rtnVal = null;
        if(ItemType.OUTER.toString().equals(itemVo.getType())) {
            rtnVal = outer.store.id.eq(itemVo.getStoreId());
        } else if(ItemType.TOP.toString().equals(itemVo.getType())) {
            rtnVal = top.store.id.eq(itemVo.getStoreId());
        } else if(ItemType.BOTTOM.toString().equals(itemVo.getType())) {
            rtnVal = bottom.store.id.eq(itemVo.getStoreId());
        } else if(ItemType.SHOES.toString().equals(itemVo.getType())) {
            rtnVal = shoes.store.id.eq(itemVo.getStoreId());
        } else {
            throw new APIException("GET_CLOTHING_ITEM_TYPE", "ItemType doesn't exist.");
        }

        return rtnVal;
    }

}
