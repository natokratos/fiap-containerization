package com.fiap.api.domain;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.fiap.api.domain.Order;
import com.fiap.api.types.PaymentType;
import com.fiap.api.types.OrderStatus;

@RunWith(SpringRunner.class)
public class OrderTest {

	@Test
	public void testIfCreated() {
		try {
			Order h = new Order();
			
			if(h.getUserName() != null && h.getUserName().isEmpty()) {
				Date d = new Date();
				h = new Order(0, "USER", OrderStatus.APPROVED, PaymentType.DEBIT, 1, 10.1, d);
				if(h.getOrderId() != 0 ||
						!h.getUserName().equals("USER") || 
						h.getStatus() != OrderStatus.APPROVED ||
						h.getPaymentType() != PaymentType.DEBIT ||
						h.getTotalQtty() != 1 ||
						h.getTotalValue() != 10.1 ||
						h.getLastUpdate() != d) {
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
			Order h = new Order();
			
			if(h.getUserName() != null && h.getUserName().isEmpty()) {
				Date d = new Date();
				
				h.setOrderId(0);
				h.setUserName("USER"); 
				h.setStatus(OrderStatus.APPROVED);
				h.setPaymentType(PaymentType.DEBIT);
				h.setTotalQtty(1);
				h.setTotalValue(10.1);
				h.setLastUpdate(d);			
	
				if(h.getOrderId() != 0 ||
						!h.getUserName().equals("USER") || 
						h.getStatus() != OrderStatus.APPROVED ||
						h.getPaymentType() != PaymentType.DEBIT ||
						h.getTotalQtty() != 1 ||
						h.getTotalValue() != 10.1 ||
						h.getLastUpdate() != d) {
					fail(getClass().getName() + ": ERRO Dados divergentes" );
				}
			}
		} catch(Exception e) {
			fail(getClass().getName() + ": ERRO " + e.getLocalizedMessage());			
		}
	}
	
	@Test
	public void testEquals() {
		Date d = new Date();
		Order h1 = new Order(0, "USER", OrderStatus.APPROVED, PaymentType.DEBIT, 0, 0.1, d);
		Order h2 = new Order(0, "USER", OrderStatus.APPROVED, PaymentType.DEBIT, 0, 0.1, d);
		
		assertTrue(h1.equals(h2));
	}
	
	@Test
	public void testFailEqualsOrderId() {
		Date d = new Date();
		Order h1 = new Order(0, "USER", OrderStatus.APPROVED, PaymentType.DEBIT, 0, 0.1, d);
		Order h2 = new Order(2, "USER", OrderStatus.APPROVED, PaymentType.DEBIT, 0, 0.1, d);
		
		assertFalse(h1.equals(h2));
	}

	@Test
	public void testFailEqualsUserName1() {
		Date d = new Date();
		Order h1 = new Order(0, "USER", OrderStatus.APPROVED, PaymentType.DEBIT, 0, 0.1, d);
		Order h2 = new Order(0, "USER-----", OrderStatus.APPROVED, PaymentType.DEBIT, 0, 0.1, d);
		
		assertFalse(h1.equals(h2));
	}

	@Test
	public void testFailEqualsUserName2() {
		Date d = new Date();
		Order h1 = new Order(0, "USER", OrderStatus.APPROVED, PaymentType.DEBIT, 0, 0.1, d);
		Order h3 = new Order(0, null, OrderStatus.APPROVED, PaymentType.DEBIT, 0, 0.1, d);
		
		assertFalse(h3.equals(h1));
	}
	
	@Test
	public void testFailEqualsStatus() {
		Date d = new Date();
		Order h1 = new Order(0, "USER", OrderStatus.APPROVED, PaymentType.DEBIT, 0, 0.1, d);
		Order h2 = new Order(0, "USER", OrderStatus.COMPLETED, PaymentType.DEBIT, 0, 0.1, d);
		
		assertFalse(h1.equals(h2));
	}
	
	@Test
	public void testFailEqualsPaymentType() {
		Date d = new Date();
		Order h1 = new Order(0, "USER", OrderStatus.APPROVED, PaymentType.DEBIT, 0, 0.1, d);
		Order h2 = new Order(0, "USER", OrderStatus.APPROVED, PaymentType.CREDIT, 0, 0.1, d);
		
		assertFalse(h1.equals(h2));
	}
	
	@Test
	public void testFailEqualsTotalQtty() {
		Date d = new Date();
		Order h1 = new Order(0, "USER", OrderStatus.APPROVED, PaymentType.DEBIT, 0, 0.1, d);
		Order h2 = new Order(0, "USER", OrderStatus.APPROVED, PaymentType.DEBIT, 2, 0.1, d);
		
		assertFalse(h1.equals(h2));
	}

	@Test
	public void testFailEqualsTotalValue() {
		Date d = new Date();
		Order h1 = new Order(0, "USER", OrderStatus.APPROVED, PaymentType.DEBIT, 0, 10.1, d);
		Order h2 = new Order(0, "USER", OrderStatus.APPROVED, PaymentType.DEBIT, 0, 10.4, d);
		
		assertFalse(h1.equals(h2));
	}

	
	@Test
	public void testFailEqualsLastUpdate() {
		Date d = new Date();
		Order h1 = new Order(0, "USER", OrderStatus.APPROVED, PaymentType.DEBIT, 0, 0.1, d);
		Order h2 = new Order(0, "USER", OrderStatus.APPROVED, PaymentType.DEBIT, 0, 0.1, null);
		
		assertFalse(h1.equals(h2));
	}


	@Test
	public void testHashCode() {
		Date d = new Date();
		Order h1 = new Order(0, "USER", OrderStatus.APPROVED, PaymentType.DEBIT, 0, 0.1, d);
		Order h2 = h1;
		
		assertTrue(h1.hashCode() == h2.hashCode());
	}
}
