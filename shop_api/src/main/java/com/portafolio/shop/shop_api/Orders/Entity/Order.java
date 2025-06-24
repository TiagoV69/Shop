package com.portafolio.shop.shop_api.Orders.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.portafolio.shop.shop_api.Security.Entity.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "orders") // "order" es una palabra reservada en SQL, por eso es mejor "orders"
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime orderDate;

    @Column(nullable = false)
    private BigDecimal total;

    // --- RELACIONES ---

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // Columna que será la clave foránea a la tabla 'users'
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // Ayuda a evitar bucles infinitos al serializar a JSON
    private List<OrderDetail> orderDetails;
}
