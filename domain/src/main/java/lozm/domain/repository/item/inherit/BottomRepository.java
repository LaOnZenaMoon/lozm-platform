package lozm.domain.repository.item.inherit;

import lozm.domain.entity.inheritance.Bottom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BottomRepository extends JpaRepository<Bottom, Long> {



}