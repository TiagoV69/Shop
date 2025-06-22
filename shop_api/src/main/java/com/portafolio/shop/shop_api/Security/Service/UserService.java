package com.portafolio.shop.shop_api.Security.Service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.portafolio.shop.shop_api.Security.Entity.User;
import com.portafolio.shop.shop_api.Security.Repository.UserRepository;

@Service // Marca esta clase como un Servicio de Spring
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Inyección de dependencias por constructor 
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerNewUser(User user) {
        // Lógica de negocio: antes de guardar, codificamos la contraseña
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}