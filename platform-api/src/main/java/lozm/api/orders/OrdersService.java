package lozm.api.orders;

import lombok.RequiredArgsConstructor;
import lozm.global.exception.ServiceException;
import lozm.object.dto.orders.GetOrdersDto;
import lozm.entity.delivery.Delivery;
import lozm.repository.RepositorySupport;
import lozm.repository.delivery.DeliveryRepository;
import lozm.object.vo.orders.OrdersVo;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final OrdersItemRepository ordersItemRepository;
    private final CouponRepository couponRepository;
    private final RepositorySupport repositorySupport;
    private final DeliveryRepository deliveryRepository;


    public List<GetOrdersDto> getOrdersList() {
        List<Orders> ordersList = repositorySupport.selectOrdersList();
        List<GetOrdersDto> rtnList = new ArrayList<>();
        for (Orders o : ordersList) {
            GetOrdersDto dto = GetOrdersDto.builder()
                    .ordersId(o.getId())
                    .orderDt(o.getOrderDt())
                    .ordersStatus(o.getStatus())
                    .deliveryId(o.getDelivery().getId())
                    .deliveryCountry(o.getDelivery().getAddress().getCountry())
                    .deliveryZipCode(o.getDelivery().getAddress().getZipCode())
                    .deliveryCity(o.getDelivery().getAddress().getCity())
                    .deliveryStreet(o.getDelivery().getAddress().getStreet())
                    .deliveryEtc(o.getDelivery().getAddress().getEtc())
                    .userId(o.getUser().getId())
                    .userName(o.getUser().getName())
                    .identifier(o.getUser().getIdentifier())
                    .userType(o.getUser().getType())
                    .build();

            rtnList.add(dto);
        }

        return rtnList;
    }

    @Transactional
    public void save(OrdersVo ordersVo) throws Exception {
        Optional<User> findUser = findUser(ordersVo.getUserId());
        Optional<Item> findItem = findItem(ordersVo.getItemId());
        Optional<Delivery> findDelivery = findDelivery(ordersVo.getDeliveryId());

        Long orderedPrice = findItem.get().getPrice();
        Optional<Coupon> findCoupon = null;
        if(ordersVo.getCouponId() != null) {
            findCoupon = findCoupon(ordersVo.getCouponId());
            orderedPrice = findCoupon.get().calculateOrderedPrice(orderedPrice);
        }

        Orders orders = new Orders();
        orders.insertOrders(ordersVo, findUser.get(), findDelivery.get());
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
        Optional<Orders> findOrders = findOrders(ordersVo.getId());
        findOrders.get().updateOrders(ordersVo);
    }

    @Transactional
    public void delete(OrdersVo ordersVo) {
        Optional<Orders> findOrders = findOrders(ordersVo.getId());
        findOrders.get().deleteOrders(ordersVo);
    }

    private Optional<Orders> findOrders(Long ordersId) {
        Optional<Orders> findOrders = ordersRepository.findById(ordersId);
        findOrders.orElseThrow(() -> new ServiceException("ORDERS_0002", "Order doesn't exist."));
        return findOrders;
    }

    private Optional<Coupon> findCoupon(Long couponId) {
        Optional<Coupon> findCoupon = couponRepository.findById(couponId);
        findCoupon.orElseThrow(() -> new ServiceException("ORDERS_SAVE_COUPON", "Coupon doesn't exist."));
        return findCoupon;
    }

    private Optional<Delivery> findDelivery(Long deliveryId) {
        Optional<Delivery> findDelivery = deliveryRepository.findById(deliveryId);
        findDelivery.orElseThrow(() -> new ServiceException("ORDERS_SAVE_DELIVERY", "Delivery doesn't exist."));
        return findDelivery;
    }

    private Optional<Item> findItem(Long itemId) {
        Optional<Item> findItem = itemRepository.findById(itemId);
        findItem.orElseThrow(() -> new ServiceException("ORDERS_SAVE_ITEM", "Item doesn't exist."));
        return findItem;
    }

    private Optional<User> findUser(Long userId) {
        Optional<User> findUser = userRepository.findById(userId);
        findUser.orElseThrow(() -> new ServiceException("ORDERS_SAVE_USER", "User doesn't exist."));
        return findUser;
    }

}
