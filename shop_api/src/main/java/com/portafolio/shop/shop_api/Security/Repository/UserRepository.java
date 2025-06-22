package com.portafolio.shop.shop_api.Security.Repository;

import com.portafolio.shop.shop_api.Security.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



import java.util.Optional;

@Repository // indicamos que es un bean de repositorio de Spring
public interface UserRepository extends JpaRepository<User, Long> {

    // "findByUsername" se traduce automaticamente en "SELECT * FROM users WHERE username = ?"
    Optional<User> findByUsername(String username);
}