package lozm.orders;

import lombok.RequiredArgsConstructor;
import lozm.dto.orders.GetOrdersDto;
import lozm.exception.APIException;
import lozm.vo.orders.OrdersVo;
import lozm.entity.coupon.Coupon;
import lozm.entity.item.Item;
import lozm.entity.orders.Orders;
import lozm.entity.orders.OrdersItem;
import lozm.entity.user.User;
import lozm.repository.coupon.CouponRepository;
import lozm.repository.item.ItemRepository;
import lozm.repository.orders.OrdersRepository;
import lozm.repository.orders.OrdersItemRepository;
import lozm.repository.user.UserRepository;
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


    public List<GetOrdersDto> findAllOrders() {
        List<Orders> ordersList = ordersRepository.findAll();

        return ordersList.stream().map(o -> new GetOrdersDto(
                o.getId(),
                o.getOrderDt(),
                o.getStatus())
        ).collect(toList());
    }

    @Transactional
    public void save(OrdersVo ordersVo) throws Exception {
        Orders orders = new Orders();

        Optional<User> findUser = userRepository.findById(ordersVo.getUserId());
        findUser.orElseThrow(() -> new APIException("ORDERS_SAVE_USER", "User doesn't exist."));

        Optional<Item> findItem = itemRepository.findById(ordersVo.getItemId());
        findItem.orElseThrow(() -> new APIException("ORDERS_SAVE_ITEM", "Item doesn't exist."));

        Long orderedPrice = ordersVo.getOrderedPrice();
        Optional<Coupon> findCoupon = null;
        if(ordersVo.getCouponId() != null) {
            findCoupon = couponRepository.findById(ordersVo.getCouponId());
            findCoupon.orElseThrow(() -> new APIException("ORDERS_SAVE_COUPON", "Coupon doesn't exist."));
            orderedPrice = findCoupon.get().calculateOrderedPrice(orderedPrice);
        }

        orders.insertOrders(ordersVo, findUser.get());

        ordersRepository.save(orders);

        //TODO 주문 고도화
        //1. 하나의 주문에 여러 상품 처리
        //2. 각 상품 별로 N 개의 쿠폰 적용가능하도록 처리
        OrdersItem ordersItem = new OrdersItem();
        ordersItem.insertOrdersItem(orderedPrice, ordersVo.getQuantity(), orders, findItem.get());

        ordersItemRepository.save(ordersItem);

        findItem.get().decreaseItemQuantity(ordersVo.getQuantity());
    }

    @Transactional
    public void update(OrdersVo ordersVo) throws Exception {
        Optional<Orders> findOrders = ordersRepository.findById(ordersVo.getId());
        findOrders.orElseThrow(() -> new APIException("ORDERS_0002", "Order doesn't exist."));
        findOrders.get().updateOrders(ordersVo);
    }
}
