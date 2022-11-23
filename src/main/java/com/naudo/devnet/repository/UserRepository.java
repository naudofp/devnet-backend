package com.naudo.devnet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.naudo.devnet.models.User;
import com.naudo.devnet.projection.UserProjection;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByUsername(String username);
	
	Optional<UserProjection> findUserById(Long id);
	
	Optional<UserProjection> findUserByUsernameContainingIgnoreCase(String username);
	
	boolean existsByUsername(String username);
	
}
