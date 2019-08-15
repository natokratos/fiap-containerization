package com.itau.api.entity;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.itau.api.domain.TaskStatus;

@RunWith(SpringRunner.class)
public class TaskTest {

	@Test
	public void testIfCreated() {
		try {
			Task t = new Task();
			
			if(t.getUserName() != null && t.getUserName().isEmpty()) {
				Date d  = new Date();
				
				t = new Task(0, "USER", TaskStatus.PENDING, "TASK 1", d, d, d);
				if(t.getTaskId() != 0 ||
						!t.getUserName().contentEquals("USER") ||
						!t.getTaskDescription().equals("TASK 1") ||
						t.getStatus() != TaskStatus.PENDING ||
						t.getCreationDate() != d ||
						t.getDueDate() != d ||
						t.getStartAlarmDate() != d) {
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
			Task t = new Task();
			
			if(t.getUserName() != null && t.getUserName().isEmpty()) {
				Date d  = new Date();
				
				t.setTaskId(0);
				t.setUserName("USER");
				t.setTaskDescription("TASK 1");
				t.setStatus(TaskStatus.PENDING);
				t.setCreationDate(d);
				t.setDueDate(d);
				t.setStartAlarmDate(d);
	
				if(t.getTaskId() != 0 ||
						!t.getUserName().contentEquals("USER") ||
						!t.getTaskDescription().equals("TASK 1") ||
						t.getStatus() != TaskStatus.PENDING ||
						t.getCreationDate() != d ||
						t.getDueDate() != d ||
						t.getStartAlarmDate() != d) {
					fail(getClass().getName() + ": ERRO Dados divergentes" );
				}
			}
		} catch(Exception e) {
			fail(getClass().getName() + ": ERRO " + e.getLocalizedMessage());			
		}
	}
	
	@Test
	public void testEquals() {
		Date date = new Date();
		
		Task t1 = new Task(0, "USERX1", TaskStatus.PENDING, "TASK1", date, date, date);
		Task t2 = new Task(0, "USERX1", TaskStatus.PENDING, "TASK1", date, date, date);
		
		assertTrue(t1.equals(t2));
	}

	@Test
	public void testFailEqualsTaskId() {
		Date date = new Date();
		
		Task t1 = new Task(0, "USERX1", TaskStatus.PENDING, "TASK1", date, date, date);
		Task t2 = new Task(1, "USERX1", TaskStatus.PENDING, "TASK1", date, date, date);
		
		assertFalse(t1.equals(t2));
	}

	@Test
	public void testFailEqualsUserName1() {
		Date date = new Date();
		
		Task t1 = new Task(0, "USERX1", TaskStatus.PENDING, "TASK1", date, date, date);
		Task t2 = new Task(0, "USERX1----", TaskStatus.PENDING, "TASK1", date, date, date);
		
		assertFalse(t1.equals(t2));
	}
	
	@Test
	public void testFailEqualsUserName2() {
		Date date = new Date();
		
		Task t1 = new Task(0, "USERX1", TaskStatus.PENDING, "TASK1", date, date, date);
		Task t3 = new Task(0, null, TaskStatus.PENDING, "TASK1", date, date, date);
		
		assertFalse(t3.equals(t1));
	}
	
	@Test
	public void testFailEqualsStatus() {
		Date date = new Date();
		
		Task t1 = new Task(0, "USERX1", TaskStatus.PENDING, "TASK1", date, date, date);
		Task t2 = new Task(0, "USERX1", TaskStatus.COMPLETED, "TASK1", date, date, date);
		
		assertFalse(t1.equals(t2));
	}

	@Test
	public void testFailEqualsTaskDescription1() {
		Date date = new Date();
		
		Task t1 = new Task(0, "USERX1", TaskStatus.PENDING, "TASK1", date, date, date);
		Task t2 = new Task(0, "USERX1", TaskStatus.PENDING, "TASK---", date, date, date);
		
		assertFalse(t1.equals(t2));
	}
	
	@Test
	public void testFailEqualsTaskDescription2() {
		Date date = new Date();
		
		Task t1 = new Task(0, "USERX1", TaskStatus.PENDING, "TASK1", date, date, date);
		Task t3 = new Task(0, "USERX1", TaskStatus.PENDING, null, date, date, date);
		
		assertFalse(t3.equals(t1));
	}
	
	@Test
	public void testFailEqualsCreationDate1() {
		Date date = new Date();
		
		Task t1 = new Task(0, "USERX1", TaskStatus.PENDING, "TASK1", date, date, date);
		Task t2 = new Task(0, "USERX1", TaskStatus.PENDING, "TASK1", null, date, date);
		
		assertFalse(t1.equals(t2));
	}
	
	@Test
	public void testFailEqualsCreationDate2() {
		Date date = new Date();
		
		Task t1 = new Task(0, "USERX1", TaskStatus.PENDING, "TASK1", date, date, date);
		Task t3 = new Task(0, "USERX1", TaskStatus.PENDING, "TASK1", null, date, date);
		
		assertFalse(t3.equals(t1));
	}
	
	@Test
	public void testFailEqualsDueDate1() {
		Date date = new Date();
		
		Task t1 = new Task(0, "USERX1", TaskStatus.PENDING, "TASK1", date, date, date);
		Task t2 = new Task(0, "USERX1", TaskStatus.PENDING, "TASK1", date, null, date);
		
		assertFalse(t1.equals(t2));
	}

	@Test
	public void testFailEqualsDueDate2() {
		Date date = new Date();
		
		Task t1 = new Task(0, "USERX1", TaskStatus.PENDING, "TASK1", date, date, date);
		Task t3 = new Task(0, "USERX1", TaskStatus.PENDING, "TASK1", date, null, date);
		
		assertFalse(t3.equals(t1));
	}
	
	@Test
	public void testFailEqualsStartAlarmDate1() {
		Date date = new Date();
		
		Task t1 = new Task(0, "USERX1", TaskStatus.PENDING, "TASK1", date, date, date);
		Task t2 = new Task(0, "USERX1", TaskStatus.PENDING, "TASK1", date, date, null);
		
		assertFalse(t1.equals(t2) );
	}
	
	@Test
	public void testFailEqualsStartAlarmDate2() {
		Date date = new Date();
		
		Task t1 = new Task(0, "USERX1", TaskStatus.PENDING, "TASK1", date, date, date);
		Task t3 = new Task(0, "USERX1", TaskStatus.PENDING, "TASK1", date, date, null);
		
		assertFalse(t3.equals(t1));
	}
	
	@Test
	public void testHashCode() {
		Date date = new Date();
		
		Task t1 = new Task(0, "USERX1", TaskStatus.PENDING, "TASK1", date, date, date);
		Task t2 = t1;
		
		assertTrue(t1.hashCode() == t2.hashCode());
	}
}
