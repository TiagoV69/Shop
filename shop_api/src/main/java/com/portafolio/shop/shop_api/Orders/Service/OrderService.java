package com.portafolio.shop.shop_api.Orders.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.portafolio.shop.shop_api.Orders.DTO.CreateOrderRequestDto;
import com.portafolio.shop.shop_api.Orders.DTO.OrderItemDto;
import com.portafolio.shop.shop_api.Orders.Entity.Order;
import com.portafolio.shop.shop_api.Orders.Entity.OrderDetail;
import com.portafolio.shop.shop_api.Orders.Repository.OrderRepository;
import com.portafolio.shop.shop_api.Products.Entity.Product;
import com.portafolio.shop.shop_api.Products.Repository.ProductRepository;
import com.portafolio.shop.shop_api.Security.Entity.User;

import jakarta.transaction.Transactional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    // Este método es una transacción. O todo se completa con éxito, o se revierte todo.
    @Transactional
    public Order createOrder(CreateOrderRequestDto orderDto, User user) {
        // Preparamos el objeto principal del Pedido
        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());

        List<OrderDetail> details = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        // Procesamos cada artículo de la "lista de la compra"
        for (OrderItemDto itemDto : orderDto.getItems()) {
            Product product = productRepository.findById(itemDto.getProductId())
                    .orElseThrow(() -> new IllegalStateException("Producto con ID " + itemDto.getProductId() + " no encontrado."));

            if (product.getStock() < itemDto.getQuantity()) {
                throw new IllegalStateException("No hay stock suficiente para el producto: " + product.getName());
            }

            // Creamos la línea de detalle para este producto
            OrderDetail detail = new OrderDetail();
            detail.setProduct(product);
            detail.setOrder(order);
            detail.setQuantity(itemDto.getQuantity());
            detail.setPrice(product.getPrice());
            details.add(detail);

            // Sumamos el coste al total del pedido
            total = total.add(product.getPrice().multiply(BigDecimal.valueOf(itemDto.getQuantity())));

            // Actualizamos el stock del producto
            product.setStock(product.getStock() - itemDto.getQuantity());
            productRepository.save(product);
        }

        // Asignamos los detalles y el total a nuestro pedido
        order.setOrderDetails(details);
        order.setTotal(total);

        // Guardamos el pedido completo en la base de datos.
        return orderRepository.save(order);
    }
}