package dede.srm.repo.interf;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import dede.srm.models.UserRole;
import dede.srm.models.enums.ERole;

public interface UserRoleRepo extends CrudRepository<UserRole, Integer> {
	Optional<UserRole> findByName(ERole name);
}
