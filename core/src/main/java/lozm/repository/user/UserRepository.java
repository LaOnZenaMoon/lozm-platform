package lozm.repository.user;

import lozm.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT U FROM User U WHERE U.identifier = :identifier")
    List<User> findByIdentifier(@Param("identifier") String identifier);

    @Query("SELECT U FROM User U WHERE U.flag = 1")
    List<User> selectUserList();

}