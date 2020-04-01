package lozm.api.orders;

import lombok.RequiredArgsConstructor;
import lozm.core.dto.orders.GetOrdersDto;
import lozm.core.dto.orders.PostOrdersDto;
import lozm.core.dto.orders.PutOrdersDto;
import lozm.core.exception.APIException;
import lozm.domain.entity.coupon.Coupon;
import lozm.domain.entity.item.Item;
import lozm.domain.entity.orders.Orders;
import lozm.domain.entity.orders.OrdersItem;
import lozm.domain.entity.user.User;
import lozm.domain.repository.coupon.CouponRepository;
import lozm.domain.repository.item.ItemRepository;
import lozm.domain.repository.orders.OrdersRepository;
import lozm.domain.repository.ordersItem.OrdersItemRepository;
import lozm.domain.repository.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final OrdersItemRepository ordersItemRepository;
    private final CouponRepository couponRepository;


    public List<GetOrdersDto.Response> findAllOrders() {
        List<Orders> ordersList = ordersRepository.findAll();

        return ordersList.stream().map(o -> new GetOrdersDto.Response(o.getId(), o.getOrderDt(), o.getStatus()))
                .collect(toList());
    }

    @Transactional
    public void save(PostOrdersDto.Request reqDto) throws Exception {
        Orders orders = new Orders();

        Optional<User> findUser = userRepository.findById(reqDto.getUserId());
        findUser.orElseThrow(() -> new APIException("ORDERS_SAVE_USER", "User doesn't exist."));

        Optional<Item> findItem = itemRepository.findById(reqDto.getItemId());
        findItem.orElseThrow(() -> new APIException("ORDERS_SAVE_ITEM", "Item doesn't exist."));

        Long orderedPrice = reqDto.getOrderedPrice();
        Optional<Coupon> findCoupon = null;
        if(reqDto.getCouponId() != null) {
            findCoupon = couponRepository.findById(reqDto.getCouponId());
            findCoupon.orElseThrow(() -> new APIException("ORDERS_SAVE_COUPON", "Coupon doesn't exist."));
            orderedPrice = findCoupon.get().calculateOrderedPrice(orderedPrice);
        }

        orders.insertOrders(reqDto, findUser.get());

        ordersRepository.save(orders);

        //TODO 주문 고도화
        //1. 하나의 주문에 여러 상품 처리
        //2. 각 상품 별로 N 개의 쿠폰 적용가능하도록 처리
        OrdersItem ordersItem = new OrdersItem();
        ordersItem.insertOrdersItem(orderedPrice, reqDto.getQuantity(), orders, findItem.get());

        ordersItemRepository.save(ordersItem);

        findItem.get().decreaseItemQuantity(reqDto.getQuantity());
    }

    @Transactional
    public void update(PutOrdersDto.Request reqDto) throws Exception {
        Optional<Orders> findOrders = ordersRepository.findById(reqDto.getId());
        findOrders.orElseThrow(() -> new APIException("ORDERS_0002", "Order doesn't exist."));
        findOrders.get().updateOrders(reqDto);
    }
}