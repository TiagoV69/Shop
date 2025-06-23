package com.portafolio.shop.shop_api.Config;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration // Le dice a Spring que esta clase contiene configuraciones
@EnableWebSecurity // Habilita la configuración de seguridad web de Spring
public class SecurityConfig {

    @Bean // Le dice a Spring que cree un objeto de este tipo y guardelo para cuando alguien lo necesite
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Deshabilitamos CSRF por ahora, entiendo que es comun en APIs REST
            .authorizeHttpRequests(authz -> authz
                // Le decimos a Spring Security que permita todas las peticiones a /auth/****
                .requestMatchers("/auth/**").permitAll() 
                // Para cualquier otra peticion, el usuario debe estar autenticado
                .anyRequest().authenticated() 
            )
            .httpBasic(withDefaults()); // OJO uso la basica por ahora luego se cambia por JWT
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}