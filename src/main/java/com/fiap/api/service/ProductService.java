package com.fiap.api.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.api.domain.Product;
import com.fiap.api.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	Logger logger = LoggerFactory.getLogger(ProductService.class);

	public Optional<Product> getProduct(long productId) {
		return productRepository.findById(productId);
	}

	public List<Product> getProducts() {
		return productRepository.findAll();
	}

}
