package lozm.domain.repository.item.inherit;

import lozm.domain.entity.inheritance.Top;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopRepository extends JpaRepository<Top, Long> {



}