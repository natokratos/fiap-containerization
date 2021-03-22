package com.fiap.api.controller;

import static org.junit.Assert.*;

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

import com.fiap.api.controller.OrderController;
import com.fiap.api.domain.Order;
import com.fiap.api.domain.OrderItem;
import com.fiap.api.main.OrderApplication;
import com.fiap.api.repository.OrderItemRepository;
import com.fiap.api.repository.OrderRepository;
import com.fiap.api.service.OrderService;
import com.fiap.api.wrapper.OrderRequestWrapper;
import com.fiap.api.types.OrderStatus;
import com.fiap.api.types.PaymentType;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderApplication.class)
public class OrderControllerTest {

	@Autowired
	public OrderController orderController;

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
		if(orderController == null) {
			fail(getClass().getName() + ": ERRO Creating Order Controller");		
		}
		
		if(o == null) {
			fail(getClass().getName() + ": ERRO Creating Order Repository");		
		}
		
		if(oi == null) {
			fail(getClass().getName() + ": ERRO Creating Order Item Repository");		
		}
	}
	
	@Test
	public void testGetOrder() {
		try {
			Map<Optional<Order>, List<Optional<OrderItem>>> order = orderController.getOrder(1);
			
			assertTrue(order != null);
		} catch(Exception e) {
			fail(getClass().getName() + ": ERRO " + e.getLocalizedMessage());
		}
	}
	
	@Test
	public void testAddOrder() {	
		OrderRequestWrapper orw = new OrderRequestWrapper();
		List<OrderItem> items = new ArrayList<OrderItem>();
		
		try {
			orw.order = new Order(4, "USERX5", OrderStatus.PENDING_PAYMENT, PaymentType.PIX, 1, 10.1, new Date());
			items.add(new OrderItem(4, 1, "ITEM", 1, new Date()));
			orw.orderItem = items;
					
			orderController.setTest(true);
			orderController.addOrder(orw);
			
			Map<Optional<Order>, List<Optional<OrderItem>>> order = orderController.getOrder(4);
			
			assertTrue(order != null);
		} catch(Exception e) {
			fail(getClass().getName() + ": ERRO " + e.getLocalizedMessage());
		}
		
		orderService.deleteOrder(orw.order);
		orderService.deleteOrderItems(orw.orderItem);
	}
	
}
