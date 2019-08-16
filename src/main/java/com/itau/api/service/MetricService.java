package com.itau.api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itau.api.domain.OperationType;
import com.itau.api.repository.HistoryRepository;

@Service
public class MetricService {
	
	@Autowired
	public HistoryRepository historyRepository;
	
	public int getTotalRequests() {
		return historyRepository.counTotal();
	}

	public Map<OperationType, Long> getTotalRequestsByOperationType() {
		HashMap<OperationType, Long> result = new HashMap<OperationType, Long> ();
		List<Object> listRows = historyRepository.countByOperationType();
		
		for (Object columns : listRows) {
			Object[] row = (Object[]) columns;
			result.put((OperationType)row[0], (Long)row[1]);
		}
		
		return result;
	}

	public Map<String, Long> getTotalRequestsByUser() {
		HashMap<String, Long> result = new HashMap<String, Long> ();
		List<Object> listRows = historyRepository.countByUser();
		
		for (Object columns : listRows) {
			Object[] row = (Object[]) columns;
			result.put((String)row[0], (Long)row[1]);
		}
		
		return result;
	}
	
	public double getAverageProcessTime() {
		return ((historyRepository.sumProcessTime() / 1000) / getTotalRequests());
	}
}
