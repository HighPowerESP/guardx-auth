package com.guardx.auth_service.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guardx.auth_service.dto.LoginRequest;
import com.guardx.auth_service.dto.UserRegistrationRequest;
import com.guardx.auth_service.service.UserService;
import com.guardx.auth_service.util.JwtUtil;

@RestController
@RequestMapping("/auth/")
public class AuthController {

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
    private UserService userService;
	
	@PostMapping("register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationRequest request) {
        try {
            userService.registerUser(request);
            return ResponseEntity.ok("Usuario registrado con éxito");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
	
	@PostMapping("login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        try {
            String token = userService.login(loginRequest);
            return ResponseEntity.ok(token); // Devuelve el JWT si es exitoso
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // Devuelve error si falla
        }
    }
	
	@GetMapping("/validate-token")
    public ResponseEntity<String> validateToken(@RequestHeader("Authorization") String token) {
        try {
            token = token.replace("Bearer ", "");
            boolean isValid = jwtUtil.isTokenExpired(token);
            if (!isValid) {
                return ResponseEntity.ok("Token válido");
            } else {
                return ResponseEntity.status(401).body("Token expirado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Token inválido");
        }
    }
	
	@GetMapping("/validate")
    public ResponseEntity<Map<String, String>> validateUsrToken(@RequestHeader("Authorization") String token) {
        try {
            String jwt = token.startsWith("Bearer ") ? token.substring(7) : token;
            String userId = jwtUtil.extractUsername(jwt);
            Map<String, String> response = new HashMap<>();
            response.put("userId", userId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("error", "Invalid token"));
        }
    }
	

}
