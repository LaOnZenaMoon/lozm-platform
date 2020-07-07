package lozm.repository.store;

import lozm.entity.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long> {

    @Query("SELECT S FROM Store S WHERE S.name = :name")
    List<Store> findByName(@Param("name") String name);

    @Query("SELECT S FROM Store S WHERE S.flag = 1")
    List<Store> selectStoreList();

}