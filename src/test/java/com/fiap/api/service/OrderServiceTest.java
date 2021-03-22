package com.fiap.api.service;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.fiap.api.domain.Order;
import com.fiap.api.domain.OrderItem;
import com.fiap.api.main.OrderApplication;
import com.fiap.api.repository.OrderItemRepository;
import com.fiap.api.repository.OrderRepository;
import com.fiap.api.types.OrderStatus;
import com.fiap.api.types.PaymentType;
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderApplication.class)
public class OrderServiceTest {

	@Autowired
	public OrderService orderService;
	
	@Autowired
	public OrderRepository o;

	@Autowired
	public OrderItemRepository oi;
	
	public Date date = new Date();
	
	@Before
	public void setup() {
		o.save(new Order(0, "USERX1", OrderStatus.APPROVED, PaymentType.DEBIT, 1, 10.1, new Date()));
		o.save(new Order(1, "USERX2", OrderStatus.COMPLETED, PaymentType.CREDIT, 1, 10.1, new Date()));
		o.save(new Order(2, "USERX3", OrderStatus.PAYMENT_APPROVED, PaymentType.PAYPAL, 1, 10.1, new Date()));
		o.save(new Order(3, "USERX4", OrderStatus.PENDING_PAYMENT, PaymentType.PIX, 1, 10.1, new Date()));
		
		oi.save(new OrderItem(0, 1, "ITEM", 1, new Date()));
		oi.save(new OrderItem(1, 1, "ITEM", 1, new Date()));
		oi.save(new OrderItem(2, 1, "ITEM", 1, new Date()));
		oi.save(new OrderItem(3, 1, "ITEM", 1, new Date()));
	}
	
	@Test
	public void testCreation() {
		if(orderService == null) {
			fail(getClass().getName() + ": ERRO Creating Order Service");		
		}
		
		if(o == null) {
			fail(getClass().getName() + ": ERRO Creating Order Repository");		
		}

		if(oi == null) {
			fail(getClass().getName() + ": ERRO Creating Order Item Repository");		
		}
	}

	@Test
	public void testAddOrder() {
		Order o = new Order(4, "USERX100", OrderStatus.APPROVED, PaymentType.DEBIT, 1, 10.1, new Date());
		try {
			orderService.saveOrder(o, true);
			
			Optional<Order> order = orderService.getOrder(4);
			
			assertTrue(order != null);
		} catch(Exception e) {
			fail(getClass().getName() + ": ERRO " + e.getLocalizedMessage());
		}
		
		orderService.deleteOrder(o);
	}
	
	@Test
	public void testAddOrderItem() {
		List<OrderItem> items = new ArrayList<OrderItem>();
		
		try {
			items.add(new OrderItem(0, 2, "ITEM2", 1, date));
			items.add(new OrderItem(0, 3, "ITEM3", 1, date));
			
			orderService.saveOrderItems(items);
			
			List<Optional<OrderItem>> order = orderService.getOrderItems(0);
			
			assertTrue(order != null);
			assertTrue(order.size() > 0);
		} catch(Exception e) {
			fail(getClass().getName() + ": ERRO " + e.getLocalizedMessage());
		}
		
		orderService.deleteOrderItems(items);
	}
		
		
	@Test
	public void testGetOrder() {
		try {
			Optional<Order> o = orderService.getOrder(1);
			
			if (o == null) {
				fail(getClass().getName() + ": ERRO Order not found");
			}
			
			assertTrue(o != null);
		} catch(Exception e) {
			fail(getClass().getName() + ": ERRO " + e.getLocalizedMessage());
		}
	}

	@Test
	public void testGetOrderItem() {
		try {
			List<Optional<OrderItem>> o = orderService.getOrderItems(1L);
			
			if (o == null) {
				fail(getClass().getName() + ": ERRO Order not found");
			}
			
			assertTrue(o != null);
			assertTrue(o.size() > 0);
		} catch(Exception e) {
			fail(getClass().getName() + ": ERRO " + e.getLocalizedMessage());
		}
	}
	
	@Test
	public void testGetTotalOrders() {
		try {
			int o = orderService.getTotalOrders();
			
			assertTrue(o > 0);
		} catch(Exception e) {
			fail(getClass().getName() + ": ERRO " + e.getLocalizedMessage());
		}
	}

	@Test
	public void testGetTotalRequestsByUser() {
		try {
			Map<String, Long> o = orderService.getTotalRequestsByUser();
			
			if (o == null) {
				fail(getClass().getName() + ": ERRO Orders not found");
			}

			assertTrue(o.size() > 0);
		} catch(Exception e) {
			fail(getClass().getName() + ": ERRO " + e.getLocalizedMessage());
		}
	}
	
}
