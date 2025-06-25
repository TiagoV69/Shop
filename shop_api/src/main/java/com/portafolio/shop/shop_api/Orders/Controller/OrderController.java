package com.portafolio.shop.shop_api.Orders.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.portafolio.shop.shop_api.Orders.DTO.CreateOrderRequestDto;
import com.portafolio.shop.shop_api.Orders.Entity.Order;
import com.portafolio.shop.shop_api.Orders.Service.OrderService;
import com.portafolio.shop.shop_api.Security.Entity.User;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @PreAuthorize("hasRole('CLIENT')") // Solo los clientes pueden crear pedidos
    public ResponseEntity<?> createOrder(
            @RequestBody CreateOrderRequestDto orderDto,
            @AuthenticationPrincipal User user) { // Spring nos inyecta el usuario del token
        try {
            Order createdOrder = orderService.createOrder(orderDto, user);
            return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            // Si el servicio lanza un error (ej: sin stock), lo capturamos y devolvemos una mala petición.
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}