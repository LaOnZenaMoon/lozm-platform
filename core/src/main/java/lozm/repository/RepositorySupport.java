package lozm.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lozm.global.exception.ServiceException;
import lozm.object.code.ItemType;
import lozm.entity.delivery.Delivery;
import lozm.entity.inheritance.Bottom;
import lozm.entity.inheritance.Outer;
import lozm.entity.inheritance.Shoes;
import lozm.entity.inheritance.Top;
import lozm.entity.item.Item;
import lozm.entity.orders.Orders;
import lozm.entity.user.User;
import lozm.object.vo.item.ItemVo;
import lozm.object.vo.sign.SignVo;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static lozm.entity.delivery.QDelivery.delivery;
import static lozm.entity.inheritance.QBottom.bottom;
import static lozm.entity.inheritance.QOuter.outer;
import static lozm.entity.inheritance.QShoes.shoes;
import static lozm.entity.inheritance.QTop.top;
import static lozm.entity.item.QItem.item;
import static lozm.entity.orders.QOrders.orders;
import static lozm.entity.user.QUser.user;


@Repository
public class RepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    public RepositorySupport(JPAQueryFactory jpaQueryFactory) {
        super(User.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }


    public List<User> selectUserDetail(SignVo signVo) {
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

    public List<Outer> selectOuterList(ItemVo itemVo) {
        return jpaQueryFactory
                .select(Projections.fields(
                        Outer.class,
                        outer.id,
                        outer.name,
                        outer.price,
                        outer.quantity,
                        outer.type,
                        outer.clothing,
                        outer.store
                ))
                .from(outer)
                .where(
                        outer.flag.eq(1)
                                .and(outer.type.eq(itemVo.getType()))
                                .and(checkStoreId(itemVo))
                )
                .fetch();
    }

    public List<Top> selectTopList(ItemVo itemVo) {
        return jpaQueryFactory
                .select(Projections.fields(
                        Top.class,
                        top.id,
                        top.name,
                        top.price,
                        top.quantity,
                        top.type,
                        top.clothing,
                        top.store
                ))
                .from(top)
                .where(
                        top.flag.eq(1)
                                .and(top.type.eq(itemVo.getType()))
                                .and(checkStoreId(itemVo))
                )
                .fetch();
    }

    public List<Bottom> selectBottomList(ItemVo itemVo) {
        return jpaQueryFactory
                .select(Projections.fields(
                        Bottom.class,
                        bottom.id,
                        bottom.name,
                        bottom.price,
                        bottom.quantity,
                        bottom.type,
                        bottom.clothing,
                        bottom.store
                ))
                .from(bottom)
                .where(
                        bottom.flag.eq(1)
                                .and(bottom.type.eq(itemVo.getType()))
                                .and(checkStoreId(itemVo))
                )
                .fetch();
    }

    public List<Shoes> selectShoesList(ItemVo itemVo) {
        return jpaQueryFactory
                .select(Projections.fields(
                        Shoes.class,
                        shoes.id,
                        shoes.name,
                        shoes.price,
                        shoes.quantity,
                        shoes.type,
                        shoes.clothing,
                        shoes.store
                ))
                .from(shoes)
                .where(
                        shoes.flag.eq(1)
                                .and(shoes.type.eq(itemVo.getType()))
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
            throw new ServiceException("GET_CLOTHING_ITEM_TYPE", "ItemType doesn't exist.");
        }

        return rtnVal;
    }

    public List<Item> selectItemList() {
        return jpaQueryFactory
                .select(item)
                .from(item)
                .where(
                        item.flag.eq(1)
                )
                .fetch();
    }

    public List<Delivery> selectDeliveryList() {
        return jpaQueryFactory
                .select(delivery)
                .from(delivery)
                .where(
                        delivery.flag.eq(1)
                )
                .fetch();
    }

    public List<Orders> selectOrdersList() {
        return jpaQueryFactory
                .select(orders)
                .from(orders)
                .where(
                        orders.flag.eq(1)
                )
                .fetch();
    }

    public List<User> selectUserInfoForJwt(SignVo signVo) {
        return jpaQueryFactory
                .select(Projections.fields(
                        User.class,
                        user.id,
                        user.name,
                        user.identifier,
                        user.password,
                        user.type
                ))
                .from(user)
                .where(
                        user.flag.eq(1)
                        .and(user.identifier.eq(signVo.getIdentifier()))
                ).fetch();
    }
}
