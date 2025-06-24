package com.portafolio.shop.shop_api.Products.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portafolio.shop.shop_api.Products.Entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Por ahora, no necesitamos métodos personalizados. JpaRepository nos da todo el CRUD.
}