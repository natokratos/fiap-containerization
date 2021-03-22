package com.fiap.api.domain;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.fiap.api.domain.OrderItem;

@RunWith(SpringRunner.class)
public class OrderItemTest {

	@Test
	public void testIfCreated() {
		try {
			OrderItem o = new OrderItem();
			
			if(o.getItemDescription() != null && o.getItemDescription().isEmpty()) {
				Date d = new Date();
				o = new OrderItem(0L, 1L, "ITEM", 1, d);
				if(o.getOrderId() != 0L ||
						o.getItemId() != 1L ||
						!o.getItemDescription().equals("ITEM") || 
						o.getItemQtty() != 1 ||
						o.getLastUpdate() != d) {
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
			OrderItem o = new OrderItem();
			
			if(o.getItemDescription() != null && o.getItemDescription().isEmpty()) {
				Date d = new Date();
				
				o.setOrderId(0);
				o.setItemId(1);
				o.setItemDescription("ITEM"); 
				o.setItemQtty(1);
				o.setLastUpdate(d);			
	
				if(o.getOrderId() != 0L ||
						o.getItemId() != 1L ||
						!o.getItemDescription().equals("ITEM") || 
						o.getItemQtty() != 1 ||
						o.getLastUpdate() != d) {
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
		OrderItem o1 = new OrderItem(0, 1, "ITEM", 1, d);
		OrderItem o2 = new OrderItem(0, 1, "ITEM", 1, d);
		
		assertTrue(o1.equals(o2));
	}

	@Test
	public void testFailEqualsItemId() {
		Date d = new Date();
		OrderItem o1 = new OrderItem(0, 1, "ITEM", 1, d);
		OrderItem o2 = new OrderItem(0, 2, "ITEM", 1, d);
		
		assertFalse(o1.equals(o2));
	}
	
	@Test
	public void testFailEqualsItemDescription1() {
		Date d = new Date();
		OrderItem o1 = new OrderItem(0, 1, "ITEM", 1, d);
		OrderItem o2 = new OrderItem(0, 1, "ITEM-----", 1, d);		
		
		assertFalse(o1.equals(o2));
	}

	@Test
	public void testFailEqualsItemDescription2() {
		Date d = new Date();
		OrderItem o1 = new OrderItem(0, 1, "ITEM", 1, d);
		OrderItem o3 = new OrderItem(0, 1, null, 1, d);
		
		assertFalse(o3.equals(o1));
	}
	
	@Test
	public void testFailEqualsTaskId() {
		Date d = new Date();
		OrderItem o1 = new OrderItem(0, 1, "ITEM", 1, d);
		OrderItem o2 = new OrderItem(0, 2, "ITEM", 2, d);
				
		assertFalse(o1.equals(o2));
	}
	
	@Test
	public void testFailEqualsLastUpdate() {
		Date d = new Date();
		OrderItem o1 = new OrderItem(0, 1, "ITEM", 1, d);
		OrderItem o2 = new OrderItem(0, 2, "ITEM", 2, null);
		
		assertFalse(o1.equals(o2));
	}

	@Test
	public void testHashCode() {
		Date d = new Date();
		OrderItem o1 = new OrderItem(0, 1, "ITEM", 1, d);
		OrderItem o2 = o1;
		
		assertTrue(o1.hashCode() == o2.hashCode());
	}
}
