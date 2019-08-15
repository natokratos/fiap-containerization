package com.itau.api.repository;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

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

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ItauApiTodoApplication.class)
public class HistoryRepositoryTest {

	@Autowired
	public HistoryRepository h;
	
	public static Date date = new Date();
	
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
		if(h == null) {
			fail(getClass().getName() + ": ERRO Creating History Repository");		
		}
	}

	@Test
	public void testCountTotal() {
		if(h == null) {
			fail(getClass().getName() + ": ERRO Creating History Repository");		
		}
		
		try {
			assertTrue(h.counTotal() == 5);
		} catch(Exception e) {
			fail(getClass().getName() + ": ERRO " + e.getLocalizedMessage());			
		}	
	}
	
	@Test
	public void testCountByOperationType() {
		if(h == null) {
			fail(getClass().getName() + ": ERRO Creating History Repository");		
		}
		
		try {
			assertTrue(h.countByOperationType().size() == 3);
		} catch(Exception e) {
			fail(getClass().getName() + ": ERRO " + e.getLocalizedMessage());			
		}	
	}
	
	@Test
	public void testCountByUser() {
		if(h == null) {
			fail(getClass().getName() + ": ERRO Creating History Repository");		
		}
		
		try {
			assertTrue(h.countByUser().size() == 4);
		} catch(Exception e) {
			fail(getClass().getName() + ": ERRO " + e.getLocalizedMessage());			
		}	
	}
}
