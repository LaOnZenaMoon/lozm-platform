package lozm.repository.item.inherit;

import lozm.entity.inheritance.Outer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OuterRepository extends JpaRepository<Outer, Long> {

    @Query("SELECT I FROM Outer I WHERE I.flag = 1 AND I.type = :type AND I.store.id = :storeId")
    List<Outer> selectClothingList(String type, Long storeId);

}