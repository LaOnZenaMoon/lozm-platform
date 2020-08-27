package lozm.repository.user;

import lozm.entity.auth.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<Account, Long> {

    @Query("SELECT U FROM Account U WHERE U.identifier = :identifier")
    List<Account> findByIdentifier(@Param("identifier") String identifier);

    @Query("SELECT U FROM Account U WHERE U.flag = 1")
    List<Account> selectUserList();

}