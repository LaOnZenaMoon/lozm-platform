package lozm.domain.repository.orders;

import lozm.domain.entity.orders.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {

    @Query("SELECT O FROM Orders O JOIN FETCH O.user")
    List<Orders> findAllByUserId();

}