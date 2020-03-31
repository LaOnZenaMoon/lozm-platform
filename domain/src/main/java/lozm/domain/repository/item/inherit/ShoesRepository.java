package lozm.domain.repository.item.inherit;

import lozm.domain.entity.inheritance.Shoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoesRepository extends JpaRepository<Shoes, Long> {



}