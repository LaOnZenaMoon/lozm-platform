package lozm.domain.repository;

import lozm.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT U FROM User U WHERE U.identifier = :identifier AND U.name = :name")
    List<User> findByIdentifier(
            @Param("identifier") String identifier,
            @Param("name") String name
    );

}