package lozm.repository.auth;

import lozm.entity.auth.Resources;
import lozm.entity.auth.ResourcesRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResourcesRepository extends JpaRepository<ResourcesRole, Long> {

    @Query("SELECT R FROM Resources R JOIN FETCH ResourcesRole WHERE R.type = 'URL' ORDER BY R.orderNumber DESC")
    List<Resources> findAllResources();

}