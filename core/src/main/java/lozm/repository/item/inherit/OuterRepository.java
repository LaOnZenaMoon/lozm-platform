package lozm.repository.item.inherit;

import lozm.entity.inheritance.Outer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OuterRepository extends JpaRepository<Outer, Long> {



}