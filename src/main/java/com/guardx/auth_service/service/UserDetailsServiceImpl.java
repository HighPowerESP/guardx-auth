package com.guardx.auth_service.service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.guardx.auth_service.model.Users;
import com.guardx.auth_service.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users usuario = userRepository.findById(UUID.fromString(username)).orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + username));
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        
        List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(usuario.getRole()));

        
        return new org.springframework.security.core.userdetails.User(usuario.getUsername(), usuario.getPasswordHash(), authorities);
	}

}
