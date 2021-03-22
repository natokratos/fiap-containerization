package com.fiap.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.api.domain.Product;
import com.fiap.api.service.ProductService;

@RestController
@RequestMapping("/ifood")
public class ProductController {
	
	@Autowired
	ProductService productService;
	 
	@GetMapping(value = "/products/{productId:[0-9]+}")
	public Optional<Product> getOrder(@PathVariable("proudctId") long productId) {
	    return productService.getProduct(productId);
	}

	@GetMapping(value = "/products")
	public List<Product> getTasks() {
	    return productService.getProducts();
	}	

}
