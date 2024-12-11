package com.guardx.auth_service.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Users {

	@Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
	
	
	@Column(unique=true)
	private String username;
	@Column(unique=true)
	private String email;
    private String encryptedMasterKey;  
    private String encryptedSalt;  
    private String passwordHash;
    private String role;
	
    public Users() {
	}
    
    public Users(String username, String email, String passwordHash, String role) {
		super();
		this.username = username;
		this.email = email;
		this.passwordHash = passwordHash;
		this.role = role;
	}
    
    
    
    
	
	
	
	
}
