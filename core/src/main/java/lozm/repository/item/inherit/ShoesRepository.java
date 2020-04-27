package lozm.repository.item.inherit;

import lozm.entity.inheritance.Shoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoesRepository extends JpaRepository<Shoes, Long> {

    @Query("SELECT I FROM Shoes I WHERE I.flag = 1 AND I.type = :type AND I.store.id = :storeId")
    List<Shoes> selectClothingList(String type, Long storeId);

}