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

import com.fiap.api.domain.Order;
import com.fiap.api.main.OrderApplication;
import com.fiap.api.repository.OrderRepository;
import com.fiap.api.types.PaymentType;
import com.fiap.api.types.OrderStatus;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderApplication.class)
public class OrderRepositoryTest {

	@Autowired
	public OrderRepository o;
	
	public static Date date = new Date();
	
	@Before
	public void setup() {
	    Calendar c = Calendar.getInstance();
	    
	    c.add(Calendar.DATE, -10);
	    
		o.save(new Order(0, "USERX1", OrderStatus.APPROVED, PaymentType.DEBIT, 1, 10.0, date));
		o.save(new Order(1, "USERX2", OrderStatus.COMPLETED, PaymentType.CREDIT, 3, 30.0, date));
		o.save(new Order(2, "USERX3", OrderStatus.PAYMENT_APPROVED, PaymentType.PAYPAL, 2, 20.0, date));
		o.save(new Order(3, "USERX4", OrderStatus.PENDING_PAYMENT, PaymentType.PAYPAL, 4, 40.0, date));
		o.save(new Order(4, "USERX5", OrderStatus.SENT, PaymentType.PIX, 5, 50.0, date));
	}
	
	@Test
	public void testCreation() {
		if(o == null) {
			fail(getClass().getName() + ": ERRO Creating Order Repository");		
		}
	}

	@Test
	public void testCountTotal() {
		if(o == null) {
			fail(getClass().getName() + ": ERRO Creating Order Repository");		
		}
		
		try {
			assertTrue(o.counTotal() == 5);
		} catch(Exception e) {
			fail(getClass().getName() + ": ERRO " + e.getLocalizedMessage());			
		}	
	}
	
	@Test
	public void testCountByUser() {
		if(o == null) {
			fail(getClass().getName() + ": ERRO Creating Order Repository");		
		}
		
		try {
			assertTrue(o.countByUser().size() == 5);
		} catch(Exception e) {
			fail(getClass().getName() + ": ERRO " + e.getLocalizedMessage());			
		}	
	}
}
