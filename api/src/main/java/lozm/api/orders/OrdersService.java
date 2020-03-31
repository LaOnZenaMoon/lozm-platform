package lozm.api.orders;

import lombok.RequiredArgsConstructor;
import lozm.core.dto.APIResponseDto;
import lozm.core.dto.orders.GetOrdersDto;
import lozm.core.dto.orders.PostOrdersDto;
import lozm.core.dto.orders.PutOrdersDto;
import lozm.core.exception.APIException;
import lozm.domain.entity.*;
import lozm.domain.repository.*;
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
    private final DeliveryRepository deliveryRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final OrdersItemRepository ordersItemRepository;


    public List<GetOrdersDto.Response> findAllOrders() {
        List<Orders> ordersList = ordersRepository.findAll();

        return ordersList.stream().map(o -> new GetOrdersDto.Response(o.getId(), o.getOrderDt(), o.getStatus()))
                .collect(toList());
    }

    @Transactional
    public void save(PostOrdersDto.Request reqDto) throws Exception {
        //TODO Orders 연관 엔티티 삽입 로직 추가
        Orders orders = new Orders();

        Optional<User> findUser = userRepository.findById(reqDto.getUserId());
        findUser.orElseThrow(() -> new APIException("ORDERS_SAVE_USER", "User doesn't exist."));

        Optional<Item> findItem = itemRepository.findById(reqDto.getItemId());
        findItem.orElseThrow(() -> new APIException("ORDERS_SAVE_ITEM", "Item doesn't exist."));

        Optional<Delivery> findDelivery = deliveryRepository.findById(reqDto.getDeliveryId());
        findDelivery.orElseThrow(() -> new APIException("ORDERS_SAVE_DELI", "Delivery doesn't exist."));

        orders.insertOrders(reqDto, findUser.get(), findDelivery.get());

        ordersRepository.save(orders);

        OrdersItem ordersItem = new OrdersItem();
        ordersItem.insertOrdersItem(reqDto.getOrderedPrice(), reqDto.getCount(), orders, findItem.get());

        ordersItemRepository.save(ordersItem);
    }

    @Transactional
    public void update(PutOrdersDto.Request reqDto) throws Exception {
        Optional<Orders> findOrders = ordersRepository.findById(reqDto.getId());
        findOrders.orElseThrow(() -> new APIException("ORDERS_0002", "Order doesn't exist."));
        findOrders.get().updateOrders(reqDto);
    }
}
