package com.fiap.api.repository;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.fiap.api.domain.OrderItem;
import com.fiap.api.main.OrderApplication;
import com.fiap.api.repository.OrderItemRepository;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderApplication.class)
public class OrderItemRepositoryTest {

	@Autowired
	public OrderItemRepository o;
	
	public static Date date = new Date();
	
	@Before
	public void setup() {
	    Calendar c = Calendar.getInstance();
	    
	    c.add(Calendar.DATE, -10);
	    
		o.save(new OrderItem(0, 0, "ITEMX1", 1, date));
		o.save(new OrderItem(0, 1, "ITEMX2", 3, date));
		o.save(new OrderItem(2, 2, "ITEMX3", 3, date));
	}
	
	@Test
	public void testCreation() {
		if(o == null) {
			fail(getClass().getName() + ": ERRO Creating Order Item Repository");		
		}
	}

	@Test
	public void testCountTotal() {
		if(o == null) {
			fail(getClass().getName() + ": ERRO Creating Order Item Repository");		
		}
		
		try {
			assertTrue(o.counTotal(0L) > 0);
		} catch(Exception e) {
			fail(getClass().getName() + ": ERRO " + e.getLocalizedMessage());			
		}	
	}

}
