package com.portafolio.shop.shop_api.Security.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portafolio.shop.shop_api.Security.DTO.RegisterRequestDto;
import com.portafolio.shop.shop_api.Security.Entity.User;
import com.portafolio.shop.shop_api.Security.Service.UserService;

@RestController
@RequestMapping("/auth") // Todas las rutas en este controlador comenzaran con /auth
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequestDto registerRequestDto) {
        // Creamos un objeto User a partir de los datos del DTO
        User newUser = new User();
        newUser.setUsername(registerRequestDto.getUsername());
        newUser.setPassword(registerRequestDto.getPassword()); // La contraseña se hashea en el servicio
        newUser.setRole(registerRequestDto.getRole());

        // Llamamos al servicio para registrar al usuario
        User savedUser = userService.registerNewUser(newUser);

        // Devolvemos una respuesta HTTP 201 (Created) con el usuario guardado en el cuerpo :D
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
}
