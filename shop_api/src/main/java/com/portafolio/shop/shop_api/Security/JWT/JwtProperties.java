package com.portafolio.shop.shop_api.Security.JWT;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data // Lombok para getters y setters
@Component
@ConfigurationProperties(prefix = "jwt") // Le dice a Spring que busque propiedades que empiecen con "jwt."
public class JwtProperties {
    
    // El nombre de esta variable (secret) debe coincidir con la parte de la propiedad después del prefijo
    // jwt.secret -> secret
    private String secret;
}