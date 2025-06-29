package com.portafolio.shop.shop_api.Config;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.portafolio.shop.shop_api.Security.JWT.JwtAuthenticationFilter;
import com.portafolio.shop.shop_api.Security.Repository.UserRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration // Le dice a Spring que esta clase contiene configuraciones
@EnableWebSecurity // Habilita la configuración de seguridad web de Spring
@EnableMethodSecurity // Habilita anotaciones como @PreAuthorize
public class SecurityConfig {

    // Inyectamos tanto el repositorio como nuestro nuevo filtro
    private final UserRepository userRepository;
    private final JwtAuthenticationFilter jwtAuthenticationFilter; // Inyectamos el filtro

    // Actualizamos el constructor para recibir ambas dependencias
    public SecurityConfig(UserRepository userRepository, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.userRepository = userRepository;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter; // Asignamos el filtro
    }

    @Bean // Le dice a Spring que cree un objeto de este tipo y guárdelo para cuando alguien lo necesite
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
      @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authz -> authz
                .requestMatchers(
                    "/auth/**",
                    "/swagger-ui.html",
                    "/swagger-ui/**",
                    "/v3/api-docs/**",
                    "/swagger-resources/**",
                    "/webjars/**"
                ).permitAll() 
                // Para cualquier otra petición, el usuario debe estar autenticado
                .anyRequest().authenticated() 
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // Añadir el nuevo bean que le dice a Spring CÓMO buscar usuarios
    @Bean
    public UserDetailsService userDetailsService() {
        // Usamos una expresión lambda para implementar el método loadUserByUsername
        return username -> userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el nombre: " + username));
    }
}
