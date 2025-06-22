package com.portafolio.shop.shop_api.Security.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Anotacion de la dependencia Lombok que genera getters, setters, toString, etc.
@NoArgsConstructor // Anotación de Lombok: genera un constructor sin argumentos
@Entity
@Table(name = "users")
public class User {

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
}