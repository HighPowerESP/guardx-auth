package com.guardx.auth_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.guardx.auth_service.dto.LoginRequest;
import com.guardx.auth_service.dto.UserRegistrationRequest;
import com.guardx.auth_service.model.Users;
import com.guardx.auth_service.repository.UserRepository;
import com.guardx.auth_service.util.JwtUtil;

@Service
public class UserService {

	@Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtUtil jwtUtil;
	
	
	public void registerUser(UserRegistrationRequest request) {
		
		if (userRepository.findByUsername(request.getUsername()) != null) {
            throw new RuntimeException("El nombre de usuario ya está en uso");
        }
		
        if (userRepository.findByEmail(request.getEmail()) != null) {
            throw new RuntimeException("El correo electrónico ya está en uso");
        }
        
        String encryptedPassword = passwordEncoder.encode(request.getPassword());
        
        String defaultRole = "ADMIN";

        // Crear un nuevo usuario
        Users newUser = new Users(
            request.getUsername(),
            request.getEmail(),
            encryptedPassword,
            defaultRole
           
        );
        
        userRepository.save(newUser);

        
	}
	
	
	public String login(LoginRequest loginRequest) {
        // Buscar al usuario por username o email
        Users user = userRepository.findByUsername(loginRequest.getUsername());
        if (user == null) {
            throw new RuntimeException("Usuario no encontrado");
        }

        // Validar la contraseña
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        // Generar el token JWT
        return jwtUtil.generateToken(user.getId());
    }
	

}
