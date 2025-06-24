package com.portafolio.shop.shop_api.Security.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.portafolio.shop.shop_api.Security.DTO.AuthResponseDto;
import com.portafolio.shop.shop_api.Security.DTO.LoginRequestDto;
import com.portafolio.shop.shop_api.Security.DTO.RegisterRequestDto;
import com.portafolio.shop.shop_api.Security.Entity.User;
import com.portafolio.shop.shop_api.Security.JWT.JwtService;
import com.portafolio.shop.shop_api.Security.Service.UserService;

@RestController
@RequestMapping("/auth") // Todas las rutas en este controlador comenzaran con /auth
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

   public AuthController(UserService userService, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
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

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        // 1. Autenticar al usuario
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword())
        );

        // 2. Si la autenticación es exitosa, obtener los detalles del usuario
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = (User) userDetails; // Hacemos un cast a nuestra clase User

        // 3. Generar el token JWT
        String token = jwtService.generateToken(user);
        
        // 4. Devolver el token en la respuesta
        return ResponseEntity.ok(new AuthResponseDto(token));
    }
}
