package com.portafolio.shop.shop_api.Orders.DTO;

import lombok.Data;
import java.util.List;

// Representa la solicitud completa para crear un pedido, que es una lista de los ítems anteriores.
@Data
public class CreateOrderRequestDto {
    private List<OrderItemDto> items;
}