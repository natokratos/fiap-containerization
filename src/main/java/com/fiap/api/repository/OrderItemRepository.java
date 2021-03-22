package com.fiap.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.fiap.api.domain.OrderItem;


@Transactional
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
	
	@Query("SELECT oi FROM OrderItem oi WHERE oi.orderId = :orderId")
	public List<Optional<OrderItem>> findByOrderId(@Param("orderId") long orderId);
	
	@Query("SELECT COUNT(oi.itemId) FROM OrderItem oi WHERE oi.orderId = :orderId")
	public int counTotal(@Param("orderId") long orderId);
}
