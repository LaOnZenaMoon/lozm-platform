package lozm.domain.repository;

import lozm.domain.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopRepository extends JpaRepository<Item, Long> {



}