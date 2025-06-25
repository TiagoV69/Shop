package com.portafolio.shop.shop_api.Orders.DTO;

import lombok.Data;

// Representa una única línea en la solicitud de pedido: "Quiero X cantidad de este producto".
@Data
public class OrderItemDto {
    private Long productId;
    private int quantity;
}