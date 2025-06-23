package com.portafolio.shop.shop_api.Security.DTO;

import com.portafolio.shop.shop_api.Security.Entity.Role;
import lombok.Data;

@Data // Lombok nos da getters y setters 
public class RegisterRequestDto {
    private String username;
    private String password;
    // Permitimos que se especifique el rol al registrarse
    private Role role;
}