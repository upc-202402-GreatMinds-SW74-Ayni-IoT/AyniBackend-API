package com.greatminds.ayni.product.infrastructure.persistence.jpa.repositories;

import com.greatminds.ayni.product.domain.model.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
