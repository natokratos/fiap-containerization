package com.fiap.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.fiap.api.domain.Product;

@Transactional
public interface ProductRepository extends JpaRepository<Product, Long> {	
}
