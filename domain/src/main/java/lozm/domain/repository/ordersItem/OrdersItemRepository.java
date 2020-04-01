package lozm.domain.repository.ordersItem;

import lozm.domain.entity.orders.OrdersItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersItemRepository extends JpaRepository<OrdersItem, Long> {



}