package com.portafolio.shop.shop_api.Orders.Repository;

import com.portafolio.shop.shop_api.Orders.Entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}