package lozm.api.orders;

import lombok.RequiredArgsConstructor;
import lozm.core.dto.orders.GetOrdersDto;
import lozm.core.dto.orders.PostOrdersDto;
import lozm.core.dto.orders.PutOrdersDto;
import lozm.core.exception.APIException;
import lozm.domain.entity.Orders;
import lozm.domain.repository.OrdersRepository;
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


    public List<GetOrdersDto.Response> findAllOrders() {
        List<Orders> ordersList = ordersRepository.findAll();

        return ordersList.stream().map(o -> new GetOrdersDto.Response(o.getId(), o.getOrderDt(), o.getStatus()))
                .collect(toList());
    }

    @Transactional
    public void save(PostOrdersDto.Request reqDto) throws Exception {
        Orders orders = new Orders();
        orders.insertOrders(reqDto);

        //TODO Orders 연관 엔티티 삽입 로직 추가


    }

    @Transactional
    public void update(PutOrdersDto.Request reqDto) throws Exception {
        Optional<Orders> findOrders = ordersRepository.findById(reqDto.getId());
        findOrders.orElseThrow(() -> new APIException("ORDERS_0002", "Order doesn't exist."));
        findOrders.get().updateOrders(reqDto);
    }
}
