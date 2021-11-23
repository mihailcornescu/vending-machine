package com.mvpmatch.vendingmachine.persistence;

import com.mvpmatch.vendingmachine.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    Optional<ProductEntity> findById(Long id);
}
