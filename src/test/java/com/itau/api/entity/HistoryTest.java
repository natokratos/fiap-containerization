package com.itau.api.entity;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.itau.api.domain.OperationType;
import com.itau.api.domain.TaskStatus;

@RunWith(SpringRunner.class)
public class HistoryTest {

	@Test
	public void testIfCreated() {
		try {
			History h = new History();
			
			if(h.getUserName() != null && h.getUserName().isEmpty()) {
				Date d = new Date();
				h = new History(0, "USER", TaskStatus.PENDING, OperationType.GET, 0, 0.1, d);
				if(h.getHistId() != 0 ||
						!h.getUserName().equals("USER") || 
						h.getStatus() != TaskStatus.PENDING ||
						h.getOperationType() != OperationType.GET ||
						h.getTaskId() != 0 ||
						h.getProcessTime() != 0.1 ||
						h.getCreationDate() != d) {
					fail(getClass().getName() + ": ERRO Objeto Vazio");
				}
			} else {
				fail(getClass().getName() + ": ERRO Objeto Vazio");
			}
		} catch(Exception e) {
			fail(getClass().getName() + ": ERRO " + e.getLocalizedMessage());			
		}
	}

	@Test
	public void testIfFilled() {
		try {
			History h = new History();
			
			if(h.getUserName() != null && h.getUserName().isEmpty()) {
				Date d = new Date();
				
				h.setHistId(0);
				h.setUserName("USER"); 
				h.setStatus(TaskStatus.PENDING);
				h.setOperationType(OperationType.GET);
				h.setTaskId(0);
				h.setProcessTime(0.1);
				h.setCreationDate(d);			
	
				if(h.getHistId() != 0 ||
						!h.getUserName().equals("USER") || 
						h.getStatus() != TaskStatus.PENDING ||
						h.getOperationType() != OperationType.GET ||
						h.getTaskId() != 0 ||
						h.getProcessTime() != 0.1 ||
						h.getCreationDate() != d) {
					fail(getClass().getName() + ": ERRO Dados divergentes" );
				}
			}
		} catch(Exception e) {
			fail(getClass().getName() + ": ERRO " + e.getLocalizedMessage());			
		}
	}
	
	@Test
	public void testEquals() {
		Date d = new Date();
		History h1 = new History(0, "USER", TaskStatus.PENDING, OperationType.GET, 0, 0.1, d);
		History h2 = new History(0, "USER", TaskStatus.PENDING, OperationType.GET, 0, 0.1, d);
		
		assertTrue(h1.equals(h2));
	}
	
	@Test
	public void testFailEqualsHistId() {
		Date d = new Date();
		History h1 = new History(0, "USER", TaskStatus.PENDING, OperationType.GET, 0, 0.1, d);
		History h2 = new History(2, "USER", TaskStatus.PENDING, OperationType.GET, 0, 0.1, d);
		
		assertFalse(h1.equals(h2));
	}

	@Test
	public void testFailEqualsUserName1() {
		Date d = new Date();
		History h1 = new History(0, "USER", TaskStatus.PENDING, OperationType.GET, 0, 0.1, d);
		History h2 = new History(0, "USER-----", TaskStatus.PENDING, OperationType.GET, 0, 0.1, d);
		
		assertFalse(h1.equals(h2));
	}

	@Test
	public void testFailEqualsUserName2() {
		Date d = new Date();
		History h1 = new History(0, "USER", TaskStatus.PENDING, OperationType.GET, 0, 0.1, d);
		History h3 = new History(0, null, TaskStatus.PENDING, OperationType.GET, 0, 0.1, d);
		
		assertFalse(h3.equals(h1));
	}
	
	@Test
	public void testFailEqualsStatus() {
		Date d = new Date();
		History h1 = new History(0, "USER", TaskStatus.PENDING, OperationType.GET, 0, 0.1, d);
		History h2 = new History(0, "USER", TaskStatus.COMPLETED, OperationType.GET, 0, 0.1, d);
		
		assertFalse(h1.equals(h2));
	}
	
	@Test
	public void testFailEqualsOperationType() {
		Date d = new Date();
		History h1 = new History(0, "USER", TaskStatus.PENDING, OperationType.GET, 0, 0.1, d);
		History h2 = new History(0, "USER", TaskStatus.PENDING, OperationType.DELETE, 0, 0.1, d);
		
		assertFalse(h1.equals(h2));
	}
	
	@Test
	public void testFailEqualsTaskId() {
		Date d = new Date();
		History h1 = new History(0, "USER", TaskStatus.PENDING, OperationType.GET, 0, 0.1, d);
		History h2 = new History(0, "USER", TaskStatus.PENDING, OperationType.GET, 2, 0.1, d);
		
		assertFalse(h1.equals(h2));
	}

	@Test
	public void testFailEqualsProcessTime1() {
		Date d = new Date();
		History h1 = new History(0, "USER", TaskStatus.PENDING, OperationType.GET, 0, 0.1, d);
		History h2 = new History(0, "USER", TaskStatus.PENDING, OperationType.GET, 0, 0.4, d);
		
		assertFalse(h1.equals(h2));
	}

	@Test
	public void testFailEqualsProcessTime2() {
		Date d = new Date();
		History h1 = new History(0, "USER", TaskStatus.PENDING, OperationType.GET, 0, 0.1, d);
		History h3 = new History(0, "USER", TaskStatus.PENDING, OperationType.GET, 0, null, d);
		
		assertFalse(h3.equals(h1));
	}
	
	@Test
	public void testFailEqualsCreationDate1() {
		Date d = new Date();
		History h1 = new History(0, "USER", TaskStatus.PENDING, OperationType.GET, 0, 0.1, d);
		History h2 = new History(0, "USER", TaskStatus.PENDING, OperationType.GET, 0, 0.1, null);
		
		assertFalse(h1.equals(h2));
	}
	
	@Test
	public void testFailEqualsCreationDate2() {
		Date d = new Date();
		History h1 = new History(0, "USER", TaskStatus.PENDING, OperationType.GET, 0, 0.1, d);
		History h3 = new History(0, "USER", TaskStatus.PENDING, OperationType.GET, 0, 0.1, null);
		
		assertFalse(h3.equals(h1));
	}	

	@Test
	public void testHashCode() {
		Date d = new Date();
		History h1 = new History(0, "USER", TaskStatus.PENDING, OperationType.GET, 0, 0.1, d);
		History h2 = h1;
		
		assertTrue(h1.hashCode() == h2.hashCode());
	}
}
