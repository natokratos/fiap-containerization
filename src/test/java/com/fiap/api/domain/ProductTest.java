package com.fiap.api.domain;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.fiap.api.domain.Product;

@RunWith(SpringRunner.class)
public class ProductTest {

	@Test
	public void testIfCreated() {
		try {
			Product p = new Product();
			
			if(p.getDescription() != null && p.getDescription().isEmpty()) {
				p = new Product(0L, "PRODUCT", 10.0);
				if(p.getProductId() != 0L ||
						!p.getDescription().equals("PRODUCT")) {
					fail(getClass().getName() + ": ERRO Objeto Vazio");
				}
			} else {
				fail(getClass().getName() + ": ERRO Objeto Vazio");
			}
		} catch(Exception e) {
			fail(getClass().getName() + ": ERRO " + e.getLocalizedMessage());			
		}
	}

	@Test
	public void testIfFilled() {
		try {
			Product p = new Product();
			
			if(p.getDescription() != null && p.getDescription().isEmpty()) {
				p.setProductId(0);
				p.setDescription("PRODUCT"); 
	
				if(p.getProductId() != 0L ||
						!p.getDescription().equals("PRODUCT")) {
					fail(getClass().getName() + ": ERRO Dados divergentes" );
				}
			}
		} catch(Exception e) {
			fail(getClass().getName() + ": ERRO " + e.getLocalizedMessage());			
		}
	}
	
	@Test
	public void testEquals() {
		Product p1 = new Product(0, "PRODUCT", 10.0);
		Product p2 = new Product(0, "PRODUCT", 10.0);
		
		assertTrue(p1.equals(p2));
	}
	
	@Test
	public void testFailEqualsOrderId() {
		Product p1 = new Product(0, "PRODUCT", 10.0);
		Product p2 = new Product(2, "PRODUCT", 10.0);
		
		assertFalse(p1.equals(p2));
	}
	
	@Test
	public void testFailEqualsItemDescription1() {
		Product p1 = new Product(0, "PRODUCT", 10.0);
		Product p2 = new Product(0, "PRODUCT-----", 10.0);
		
		assertFalse(p1.equals(p2));
	}


	@Test
	public void testHashCode() {
		Product p1 = new Product(0, "PRODUCT", 10.0);
		Product p2 = p1;
		
		assertTrue(p1.hashCode() == p2.hashCode());
	}
}
