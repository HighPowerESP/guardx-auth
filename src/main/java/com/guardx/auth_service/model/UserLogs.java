package com.guardx.auth_service.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Data
@Entity
public class UserLogs {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private Long userId;  // Nombre de usuario relacionado con el log
    private String eventType;  // Tipo de evento (por ejemplo, "LOGIN", "PASSWORD_RECOVERY", etc.)
    private String ipAddress;  // Dirección IP desde la que se realizó la acción
    private String location;   // Ubicación (puede obtenerse a partir de la IP)
    private boolean success;   // Indica si la acción fue exitosa (true) o fallida (false)
    private Date timestamp; 
	
}
