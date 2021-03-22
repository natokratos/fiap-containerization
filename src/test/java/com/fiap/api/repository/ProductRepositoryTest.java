package com.fiap.api.repository;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.fiap.api.domain.Product;
import com.fiap.api.main.OrderApplication;
import com.fiap.api.repository.ProductRepository;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderApplication.class)
public class ProductRepositoryTest {

	@Autowired
	public ProductRepository p;

	@Before
	public void setup() {
	}
	
	@Test
	public void testCreation() {
		if(p == null) {
			fail(getClass().getName() + ": ERRO Creating Product Repository");		
		}
	}
	
	@Test
	public void testFindByProductId() {
		if(p == null) {
			fail(getClass().getName() + ": ERRO Creating Product Repository");		
		}
		
		try {
			assertTrue(p.findById(1L) != null);
		} catch(Exception e) {
			fail(getClass().getName() + ": ERRO " + e.getLocalizedMessage());			
		}		
	}
	
	@Test
	public void testFindAll() {
		if(p == null) {
			fail(getClass().getName() + ": ERRO Creating Product Repository");		
		}
		
		try {
			List<Product> products = p.findAll();
			assertTrue(products != null);
			assertTrue(products.size() > 0);
		} catch(Exception e) {
			fail(getClass().getName() + ": ERRO " + e.getLocalizedMessage());			
		}		
	}
}
