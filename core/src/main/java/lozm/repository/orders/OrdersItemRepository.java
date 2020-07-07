package lozm.repository.orders;

import lozm.entity.orders.OrdersItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface OrdersItemRepository extends JpaRepository<OrdersItem, Long> {



}