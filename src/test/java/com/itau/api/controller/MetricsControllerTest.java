package com.itau.api.controller;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.itau.api.domain.OperationType;
import com.itau.api.domain.TaskStatus;
import com.itau.api.entity.History;
import com.itau.api.main.ItauApiTodoApplication;
import com.itau.api.repository.HistoryRepository;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ItauApiTodoApplication.class)
public class MetricsControllerTest {

	@Autowired
	public MetricsController metricsController;

	@Autowired
	public HistoryRepository h;
	
	public Date date = new Date();
	
	@Before
	public void setup() {
	    Calendar c = Calendar.getInstance();
	    
	    c.add(Calendar.DATE, -10);
	    
		h.save(new History(0, "USERX1", TaskStatus.PENDING, OperationType.GET, 0, 0.1, date));
		h.save(new History(1, "USERX2", TaskStatus.PENDING, OperationType.ADD, 0, 0.1, date));
		h.save(new History(2, "USERX2", TaskStatus.PENDING, OperationType.GET, 0, 0.1, date));
		h.save(new History(3, "USERX4", TaskStatus.PENDING, OperationType.ADD, 0, 0.1, c.getTime()));
		h.save(new History(4, "USERX5", TaskStatus.PENDING, OperationType.UPDATE, 0, 0.1, date));
	}
	
	@Test
	public void testCreation() {
		if(metricsController == null) {
			fail(getClass().getName() + ": ERRO Creating Metrics Controller");		
		}
	}

	@Test
	public void testDoMetrics() {
		if(metricsController == null) {
			fail(getClass().getName() + ": ERRO Creating Metrics Controller");		
		}
		
		try {
			List<String> l = metricsController.doMetrics();
			
			assertTrue(l != null && l.size() == 4);
		} catch(Exception e) {
			fail(getClass().getName() + ": ERRO " + e.getLocalizedMessage());
		}
	}
}
