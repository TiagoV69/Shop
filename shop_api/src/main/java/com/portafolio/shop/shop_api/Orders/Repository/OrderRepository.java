package com.portafolio.shop.shop_api.Orders.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portafolio.shop.shop_api.Orders.Entity.Order;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Método para buscar todos los pedidos de un usuario específico
    List<Order> findByUserId(Long userId);
}   