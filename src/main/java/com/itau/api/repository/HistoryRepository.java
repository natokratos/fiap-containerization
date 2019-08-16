package com.itau.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.itau.api.entity.History;

@Transactional
public interface HistoryRepository extends JpaRepository<History, Long> {
	
	@Query("SELECT COUNT(h.histId) FROM History h")
	public int counTotal();
	
	@Query("SELECT DISTINCT h.operationType, COUNT(h.histId) FROM History h GROUP BY h.operationType")
	public List<Object> countByOperationType();
	
	@Query("SELECT DISTINCT h.userName, COUNT(h.histId) FROM History h GROUP BY h.userName")
	public List<Object> countByUser();

	@Query("SELECT SUM(h.processTime) FROM History h")
	public double sumProcessTime();
}
