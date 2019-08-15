package com.itau.api.controller;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.itau.api.domain.SeedType;
import com.itau.api.domain.TaskStatus;
import com.itau.api.entity.Seed;
import com.itau.api.entity.Task;
import com.itau.api.main.ItauApiTodoApplication;
import com.itau.api.repository.SeedRepository;
import com.itau.api.repository.TaskRepository;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ItauApiTodoApplication.class)
public class ToDoControllerTest {

	@Autowired
	public ToDoController toDoController;
	
	@Autowired
	public TaskRepository t;

	@Autowired
	public SeedRepository s;
	
	public Date date = new Date();
	
	@Before
	public void setup() {
		t.save(new Task(0, "USERX1", TaskStatus.PENDING, "DESC1", new Date(), new Date(), new Date()));
		t.save(new Task(1, "USERX1", TaskStatus.PENDING, "DESC2", new Date(), new Date(), new Date()));
		t.save(new Task(2, "USERX2", TaskStatus.PENDING, "DESC3", new Date(), new Date(), new Date()));
		t.save(new Task(3, "USERX3", TaskStatus.PENDING, "DESC4", new Date(), new Date(), new Date()));

		s.save(new Seed(SeedType.TASK, "TASK", 4));
	}
	
	@Test
	public void testCreation() {
		if(toDoController == null) {
			fail(getClass().getName() + ": ERRO Creating ToDo Controller");		
		}
		
		if(t == null) {
			fail(getClass().getName() + ": ERRO Creating Task Repository");		
		}
	}
	
	@Test
	public void testGetTasks() {
		if(toDoController == null) {
			fail(getClass().getName() + ": ERRO Creating ToDo Controller");		
		}
		
		if(t == null) {
			fail(getClass().getName() + ": ERRO Creating Task Repository");		
		}
		
		try {
			List<Task> l = toDoController.getTasks();
			
			assertTrue(l != null && l.size() == 4);
		} catch(Exception e) {
			fail(getClass().getName() + ": ERRO " + e.getLocalizedMessage());
		}
	}

	@Test
	public void testGetTasksUserName() {
		if(toDoController == null) {
			fail(getClass().getName() + ": ERRO Creating ToDo Controller");		
		}
		
		if(t == null) {
			fail(getClass().getName() + ": ERRO Creating Task Repository");		
		}
		
		try {
			List<Task> l = toDoController.getTasks("USERX1");
			
			assertTrue(l != null && l.size() == 2);
		} catch(Exception e) {
			fail(getClass().getName() + ": ERRO " + e.getLocalizedMessage());
		}
	}
	
	@Test
	public void testGetTaskId() {
		if(toDoController == null) {
			fail(getClass().getName() + ": ERRO Creating ToDo Controller");		
		}
		
		if(t == null) {
			fail(getClass().getName() + ": ERRO Creating Task Repository");		
		}
		
		try {
			Task task = toDoController.getTask(1);
			
			assertTrue(task != null);
		} catch(Exception e) {
			fail(getClass().getName() + ": ERRO " + e.getLocalizedMessage());
		}
	}
	
	@Test
	public void testAddTask() {
		if(toDoController == null) {
			fail(getClass().getName() + ": ERRO Creating ToDo Controller");		
		}
		
		if(t == null) {
			fail(getClass().getName() + ": ERRO Creating Task Repository");		
		}
		
		try {
			toDoController.addTask(new Task(0, "USERX4", TaskStatus.PENDING, "DESC4", date, date, date));
			
			Task task = toDoController.getTask(4);
			
			assertTrue(task != null);
		} catch(Exception e) {
			fail(getClass().getName() + ": ERRO " + e.getLocalizedMessage());
		}
		
		toDoController.deleteTask(4);
	}
	
	@Test
	public void testUpdateTask() {
		if(toDoController == null) {
			fail(getClass().getName() + ": ERRO Creating ToDo Controller");		
		}
		
		if(t == null) {
			fail(getClass().getName() + ": ERRO Creating Task Repository");		
		}
		
		try {
			toDoController.updateTask(new Task(2, "USERX---2", TaskStatus.PENDING, "DESC---3", new Date(), new Date(), new Date()));
			
			Task task = toDoController.getTask(2);
			
			assertTrue(task != null && task.getUserName().equals("USERX---2") && task.getTaskDescription().equals("DESC---3"));
		} catch(Exception e) {
			fail(getClass().getName() + ": ERRO " + e.getLocalizedMessage());
		}
		
		toDoController.updateTask(new Task(2, "USERX2", TaskStatus.PENDING, "DESC3", new Date(), new Date(), new Date()));
	}

	@Test
	public void testDeleteTask() {
		if(toDoController == null) {
			fail(getClass().getName() + ": ERRO Creating ToDo Controller");		
		}
		
		if(t == null) {
			fail(getClass().getName() + ": ERRO Creating Task Repository");		
		}
		
		try {
			toDoController.deleteTask(3);
			
			Task task = toDoController.getTask(3);
			
			assertTrue(task == null);
		} catch(Exception e) {
			fail(getClass().getName() + ": ERRO " + e.getLocalizedMessage());
		}
		
		t.save(new Task(3, "USERX3", TaskStatus.PENDING, "DESC4", new Date(), new Date(), new Date()));
	}
}
