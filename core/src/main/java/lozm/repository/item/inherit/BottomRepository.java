package lozm.repository.item.inherit;

import lozm.entity.inheritance.Bottom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BottomRepository extends JpaRepository<Bottom, Long> {

    @Query("SELECT I FROM Bottom I WHERE I.flag = 1 AND I.type = :type AND I.store.id = :storeId")
    List<Bottom> selectClothingList(String type, Long storeId);

}