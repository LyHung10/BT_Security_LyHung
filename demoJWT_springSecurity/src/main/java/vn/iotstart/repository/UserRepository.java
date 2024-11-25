package vn.iotstart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.iotstart.entity.user;

public interface UserRepository extends JpaRepository<user, Integer> {
	Optional<user> findbyEmail(String email);

}
