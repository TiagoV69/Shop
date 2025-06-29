# Shop
API REST para E-commerce con Spring Boot
![alt text](https://img.shields.io/badge/Java-17-blue)
![alt text](https://img.shields.io/badge/Spring%20Boot-3.x-green)
![alt text](https://img.shields.io/badge/MySQL-8.0-orange)
![alt text](https://img.shields.io/badge/Security-JWT-red)

API REST completa y profesional para un sistema de tienda online, desarrollada para demostrar habilidades avanzadas en el ecosistema Spring.
Ver la Documentación Interactiva (una vez ejecutado)http://localhost:8080/swagger-ui.html

 **Características Clave**
    *Seguridad Robusta: Autenticación con JWT y autorización basada en roles (ADMIN, CLIENT) usando Spring Security.

    *Lógica de Negocio Transaccional: Creación de pedidos con validación y actualización de stock de forma atómica (@Transactional).

    *Arquitectura Profesional: Estructura modular, uso de DTOs y capas de servicio bien definidas.
    *Gestión Completa: CRUD de productos y sistema de creación de pedidos.

    *Documentación Automática: API completamente documentada y lista para probar con OpenAPI (Swagger).

**Stack Tecnológico**
    *Lenguaje: Java 17
    *Framework: Spring Boot 3
    *Seguridad: Spring Security 6, JWT
    *Base de Datos: Spring Data JPA, Hibernate, MySQL
    *Build Tool: Maven

**Puesta en Marcha Rápida**
Clonar el repositorio:
git clone https://github.com/tu-usuario/tu-repositorio.git
cd tu-repositorio

Configurar application.properties:
Abre src/main/resources/application.properties y ajusta las credenciales de tu base de datos MySQL.
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña

Ejecutar:
mvn spring-boot:run