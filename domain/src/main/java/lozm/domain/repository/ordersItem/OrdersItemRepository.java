package lozm.domain.repository.ordersItem;

import lozm.domain.entity.OrdersItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersItemRepository extends JpaRepository<OrdersItem, Long> {



}