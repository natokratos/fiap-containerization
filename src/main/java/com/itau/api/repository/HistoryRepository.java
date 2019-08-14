package com.itau.api.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.itau.api.entity.History;

public interface HistoryRepository extends JpaRepository<History, Long> {
	
	@Query("SELECT COUNT(h.histId) FROM History h")
	public int counTotal();
	
	@Query("SELECT COUNT(h.histId) FROM History h WHERE h.creationDate = :creationDate")
	public int countByDay(@Param("creationDate") Date creationDate);
	
	@Query("SELECT COUNT(h.histId) FROM History h WHERE h.userName = :userName AND h.creationDate = :creationDate")
	public int countByUserByDay(@Param("userName") String userName, @Param("creationDate") Date creationDate);

	//public double sumAvgProcessTime();
	//public int sumAvgProcessTimeByDay();
}
