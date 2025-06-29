package com.portafolio.shop.shop_api.Security.Entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Anotacion de la dependencia Lombok que genera getters, setters, toString, etc.
@NoArgsConstructor // Anotación de Lombok: genera un constructor sin argumentos
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING) // Le dice a JPA que guarde el enum como String ("ADMIN") y no como número (0)
    @Column(nullable = false)
    private Role role;

     // METODOS REQUERIDOS POR LA INTERFAZ UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Devolvemos una lista con el rol del usuario
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getUsername() {
        // Spring Security usara este metodo para obtener el nombre de usuario
        return this.username;
    }
    
    // Para este tipo proyecto comprendo que podemos dejar estos metodos en true
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
