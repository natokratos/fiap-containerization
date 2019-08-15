package com.itau.api.service;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.itau.api.domain.OperationType;
import com.itau.api.domain.SeedType;
import com.itau.api.domain.TaskStatus;
import com.itau.api.entity.History;
import com.itau.api.entity.Seed;
import com.itau.api.main.ItauApiTodoApplication;
import com.itau.api.repository.HistoryRepository;
import com.itau.api.repository.SeedRepository;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ItauApiTodoApplication.class)
public class MetricServiceTest {

	@Autowired
	public MetricService metricService;
	
	@Autowired
	public HistoryRepository h;

	@Autowired
	public SeedRepository s;
	
	public Date date = new Date();
	
	@Before
	public void setup() {
		h.save(new History(0, "USERX1", TaskStatus.PENDING, OperationType.GET, 0, 0.1, date));
		h.save(new History(1, "USERX2", TaskStatus.PENDING, OperationType.ADD, 0, 0.1, date));
		h.save(new History(2, "USERX2", TaskStatus.PENDING, OperationType.GET, 0, 0.1, date));
		h.save(new History(3, "USERX4", TaskStatus.PENDING, OperationType.ADD, 0, 0.1, date));
		h.save(new History(4, "USERX5", TaskStatus.PENDING, OperationType.UPDATE, 0, 0.1, date));
		
		s.save(new Seed(SeedType.HISTORY, "HISTORY", 5));
	}
	
	@Test
	public void testCreation() {
		if(metricService == null) {
			fail(getClass().getName() + ": ERRO Creating Metric Service");		
		}
		
		if(h == null) {
			fail(getClass().getName() + ": ERRO Creating History Repository");		
		}
		
		if(s == null) {
			fail(getClass().getName() + ": ERRO Creating Seed Repository");		
		}	
	}

	@Test
	public void testGetTotalRequests() {
		if(metricService == null) {
			fail(getClass().getName() + ": ERRO Creating Metric Service");		
		}
		
		if(h == null) {
			fail(getClass().getName() + ": ERRO Creating History Repository");		
		}
		
		if(s == null) {
			fail(getClass().getName() + ": ERRO Creating Seed Repository");		
		}	
		
		try {
			int nRequests = metricService.getTotalRequests();

			assertTrue(nRequests == 5);
		} catch(Exception e) {
			fail(getClass().getName() + ": ERRO " + e.getLocalizedMessage());
		}
	}
	
	@Test
	public void testGetTotalRequestsByOperationType() {
		if(metricService == null) {
			fail(getClass().getName() + ": ERRO Creating Metric Service");		
		}
		
		if(h == null) {
			fail(getClass().getName() + ": ERRO Creating History Repository");		
		}
		
		if(s == null) {
			fail(getClass().getName() + ": ERRO Creating Seed Repository");		
		}	
		
		try {
			Map<OperationType, Long> m = metricService.getTotalRequestsByOperationType();
			
			if (m == null) {
				fail(getClass().getName() + ": ERRO List not found");
			}
			
			assertTrue(m.size() == 3);
		} catch(Exception e) {
			fail(getClass().getName() + ": ERRO " + e.getLocalizedMessage());
		}
	}
	
	@Test
	public void testGetTotalRequestsByUser() {
		if(metricService == null) {
			fail(getClass().getName() + ": ERRO Creating Metric Service");		
		}
		
		if(h == null) {
			fail(getClass().getName() + ": ERRO Creating History Repository");		
		}
		
		if(s == null) {
			fail(getClass().getName() + ": ERRO Creating Seed Repository");		
		}	
		
		try {
			Map<String, Long>  m = metricService.getTotalRequestsByUser();
			
			if (m == null) {
				fail(getClass().getName() + ": ERRO List not found");
			}
			
			assertTrue(m.size() == 4);
		} catch(Exception e) {
			fail(getClass().getName() + ": ERRO " + e.getLocalizedMessage());
		}
	}
	
	@Test
	public void testGetAverageProcessTime() {
		if(metricService == null) {
			fail(getClass().getName() + ": ERRO Creating Metric Service");		
		}
		
		if(h == null) {
			fail(getClass().getName() + ": ERRO Creating History Repository");		
		}
		
		if(s == null) {
			fail(getClass().getName() + ": ERRO Creating Seed Repository");		
		}	
		
		try {
			double nAvgRequests = metricService.getAverageProcessTime();

			assertTrue(nAvgRequests > 0.0);
		} catch(Exception e) {
			fail(getClass().getName() + ": ERRO " + e.getLocalizedMessage());
		}
	}
}
