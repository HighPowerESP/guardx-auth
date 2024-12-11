package com.guardx.auth_service.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guardx.auth_service.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, UUID>{

	public Users findByUsername(String username);

	public Users findByEmail(String email);
	
	public Optional<Users> findById(UUID id);
		
	
}

