package com.fiap.api.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fiap.api.domain.Order;
import com.fiap.api.domain.OrderItem;
import com.fiap.api.domain.Seed;
import com.fiap.api.repository.OrderItemRepository;
import com.fiap.api.repository.OrderRepository;
import com.fiap.api.repository.SeedRepository;
import com.fiap.api.types.SeedType;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderItemRepository orderItemRepository;
	@Autowired
	private SeedRepository seedRepository;
	
	Logger logger = LoggerFactory.getLogger(OrderService.class);
	
	public long saveOrder(Order order, boolean testId) {
		long orderId = 0L;
		
		if(testId) {
			orderId = order.getOrderId();
		} else {
			orderId = getOrderSeed();
		}
		
		Order orderToSave = new Order(orderId, 
				order.getUserName(),
				order.getStatus(), 
				order.getPaymentType(), 
				order.getTotalQtty(), 
				order.getTotalValue(), 
				new Date());
				
		orderRepository.save(orderToSave);
		
		return orderId;
	}

	public void saveOrderItems(List<OrderItem> orderItem) {
		for(OrderItem oi : orderItem) {
			oi.setItemId(getOrderItemSeed());
		}
		orderItemRepository.saveAll(orderItem);	
	}

	public void deleteOrder(Order order) {
		orderRepository.delete(order);
	}
	
	public void deleteOrderItems(List<OrderItem> orderItem) {
		orderItemRepository.deleteAll(orderItem);
	}
	
	public Optional<Order> getOrder(long orderId) {
		return orderRepository.findById(orderId);
	}

	public List<Optional<OrderItem>> getOrderItems(long orderId) {		
		return orderItemRepository.findByOrderId(orderId);
	}

	public int getTotalOrders() {
		return orderRepository.counTotal();
	}

	public Map<String, Long> getTotalRequestsByUser() {
		HashMap<String, Long> result = new HashMap<String, Long> ();
		List<Object> listRows = orderRepository.countByUser();
		
		for (Object columns : listRows) {
			Object[] row = (Object[]) columns;
			result.put((String)row[0], (Long)row[1]);
		}
		
		return result;
	}
	
	@Transactional
	int getOrderSeed() {
		Seed seed = null;
		int nSeed = 0;
		
		seed = seedRepository.findBySeedId(SeedType.ORDER);
		logger.debug("Pegando a semente para adicionar a Tarefa [ " + seed.toString() + " ]");
		
		nSeed = seed.getValue();
		seed.setValue(seed.getValue() + 1);
		
		seedRepository.save(seed);
		logger.debug("Atualizando a semente das Tarefas [ " + seed.toString() + " ]");
		
		return nSeed;
	}

	@Transactional
	int getOrderItemSeed() {
		Seed seed = null;
		int nSeed = 0;
		
		seed = seedRepository.findBySeedId(SeedType.ORDER_ITEM);
		logger.debug("Pegando a semente para adicionar a Tarefa [ " + seed.toString() + " ]");
		
		nSeed = seed.getValue();
		seed.setValue(seed.getValue() + 1);
		
		seedRepository.save(seed);
		logger.debug("Atualizando a semente das Tarefas [ " + seed.toString() + " ]");
		
		return nSeed;
	}
}
