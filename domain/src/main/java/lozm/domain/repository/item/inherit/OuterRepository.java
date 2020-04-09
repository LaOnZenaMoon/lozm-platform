package lozm.domain.repository.item.inherit;

import lozm.domain.entity.inheritance.Outer;
import lozm.domain.entity.orders.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OuterRepository extends JpaRepository<Outer, Long> {



}