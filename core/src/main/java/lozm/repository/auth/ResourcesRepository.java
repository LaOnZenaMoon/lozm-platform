package lozm.repository.auth;

import lozm.entity.auth.ResourcesRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourcesRepository extends JpaRepository<ResourcesRole, Long> {



}