package lozm.repository.item;

import lozm.entity.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("SELECT I FROM Item I WHERE I.flag = 1")
    List<Item> selectItemList();

    @Query("SELECT I FROM Item I WHERE I.store.id = :storeId AND I.flag = 1")
    List<Item> selectItemListByStoreId(@Param("storeId") Long storeId);

}