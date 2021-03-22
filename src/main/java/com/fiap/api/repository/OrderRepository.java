package com.fiap.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.fiap.api.domain.Order;

@Transactional
public interface OrderRepository extends JpaRepository<Order, Long> {
	
	@Query("SELECT COUNT(o.orderId) FROM Order o")
	public int counTotal();
	
	@Query("SELECT DISTINCT o.userName, COUNT(o.orderId) FROM Order o GROUP BY o.userName")
	public List<Object> countByUser();
}
