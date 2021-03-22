package com.fiap.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.api.domain.Order;
import com.fiap.api.domain.OrderItem;
import com.fiap.api.service.OrderService;
import com.fiap.api.wrapper.OrderRequestWrapper;

@RestController
@RequestMapping("/ifood")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	private boolean isTest = false;
	 
	@GetMapping(value = "/orders/{orderId:[0-9]+}")
	public Map<Optional<Order>,  List<Optional<OrderItem>>> getOrder(@PathVariable("orderId") long orderId) {
	    Optional<Order> order = orderService.getOrder(orderId);
	    List<Optional<OrderItem>> orderItem = orderService.getOrderItems(orderId);
	    
	    HashMap<Optional<Order>, List<Optional<OrderItem>>> orderMap = 
	    		new HashMap<Optional<Order>, List<Optional<OrderItem>>>();
	    orderMap.put(order, orderItem);
	    
	    return orderMap;
	}
	
	@PostMapping(value = "/orders")
	public void addOrder(@RequestBody OrderRequestWrapper orderRequestWrapper) {
		long orderId = orderService.saveOrder(orderRequestWrapper.order, isTest);
		for(OrderItem oi : orderRequestWrapper.orderItem) {
			oi.setOrderId(orderId);
		}

		orderService.saveOrderItems(orderRequestWrapper.orderItem);
	}

	public boolean isTest() {
		return isTest;
	}

	public void setTest(boolean isTest) {
		this.isTest = isTest;
	}
	
}
