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
import com.itau.api.entity.Task;
import com.itau.api.main.ItauApiTodoApplication;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ItauApiTodoApplication.class)
public class TaskRepositoryTest {
	
	@Autowired
	public TaskRepository t;
	
	public static Date date = new Date();
	
	@Before
	public void setup() {
		t.save(new Task(0, "USERX1", TaskStatus.PENDING, "DESC1", new Date(), new Date(), new Date()));
		t.save(new Task(1, "USERX1", TaskStatus.PENDING, "DESC2", new Date(), new Date(), new Date()));
		t.save(new Task(2, "USERX2", TaskStatus.PENDING, "DESC3", new Date(), new Date(), new Date()));
		t.save(new Task(3, "USERX3", TaskStatus.PENDING, "DESC4", new Date(), new Date(), new Date()));
	}
	
	@Test
	public void testCreation() {
		if(t == null) {
			fail(getClass().getName() + ": ERRO Creating Task Repository");		
		}
	}

	@Test
	public void testFindAll() {
		if(t == null) {
			fail(getClass().getName() + ": ERRO Creating Task Repository");		
		}
		
		try {
			assertTrue(t.findAll().size() == 4);
		} catch(Exception e) {
			fail(getClass().getName() + ": ERRO " + e.getLocalizedMessage());			
		}	
	}
	
	@Test
	public void testFindByUserName() {
		if(t == null) {
			fail(getClass().getName() + ": ERRO Creating Task Repository");		
		}
		
		try {
			assertTrue(t.findByUserName("USERX1").size() == 2);
		} catch(Exception e) {
			fail(getClass().getName() + ": ERRO " + e.getLocalizedMessage());			
		}	
	}
	
	@Test
	public void testFindByTaskId() {
		if(t == null) {
			fail(getClass().getName() + ": ERRO Creating Task Repository");		
		}
		
		try {
			assertTrue(t.findByTaskId(1).getTaskId() == 1);
		} catch(Exception e) {
			fail(getClass().getName() + ": ERRO " + e.getLocalizedMessage());			
		}	
	}

	@Test
	public void testDeleteByTaskId() {
		if(t == null) {
			fail(getClass().getName() + ": ERRO Creating Task Repository");		
		}
		
		try {
			t.deleteByTaskId(3);
			
			assertTrue(t.findByTaskId(3) == null);
		} catch(Exception e) {
			fail(getClass().getName() + ": ERRO " + e.getLocalizedMessage());			
		}
		
		t.save(new Task(3, "USERX3", TaskStatus.PENDING, "DESC4", new Date(), new Date(), new Date()));
	}	
}