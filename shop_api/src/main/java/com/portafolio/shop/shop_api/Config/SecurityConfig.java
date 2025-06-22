package com.portafolio.shop.shop_api.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration // Le dice a Spring que esta clase contiene configuraciones
public class SecurityConfig {

    @Bean // Le dice a Spring que cree un objeto de este tipo y guardelo para cuando alguien lo necesite
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}