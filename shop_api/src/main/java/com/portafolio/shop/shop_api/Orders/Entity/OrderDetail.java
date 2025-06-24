package com.portafolio.shop.shop_api.Orders.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.portafolio.shop.shop_api.Products.Entity.Product;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
@Table(name = "order_details")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private BigDecimal price; // Guardamos el precio al momento de la compra

    // --- RELACIONES ---

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    @JsonBackReference // Complemento de @JsonManagedReference para evitar bucles
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}