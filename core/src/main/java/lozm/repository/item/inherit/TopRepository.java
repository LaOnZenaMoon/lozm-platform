package lozm.repository.item.inherit;

import lozm.entity.inheritance.Top;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopRepository extends JpaRepository<Top, Long> {

    @Query("SELECT I FROM Top I WHERE I.flag = 1 AND I.type = :type AND I.store.id = :storeId")
    List<Top> selectClothingList(String type, Long storeId);

}