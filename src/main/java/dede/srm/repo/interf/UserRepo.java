package dede.srm.repo.interf;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import dede.srm.models.User;

public interface UserRepo extends CrudRepository<User, Long> {
	Optional<User> findByUsername(String username);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
}
