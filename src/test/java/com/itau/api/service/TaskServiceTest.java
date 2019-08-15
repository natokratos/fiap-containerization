package com.itau.api.service;

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
public class TaskServiceTest {

	@Autowired
	public TaskService taskService;
	
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
		if(taskService == null) {
			fail(getClass().getName() + ": ERRO Creating Task Service");		
		}
		
		if(t == null) {
			fail(getClass().getName() + ": ERRO Creating Task Repository");		
		}
		
		if(s == null) {
			fail(getClass().getName() + ": ERRO Creating Seed Repository");		
		}
	}
	
	@Test
	public void testGetTasks() {
		if(taskService == null) {
			fail(getClass().getName() + ": ERRO Creating Task Service");		
		}
		
		if(t == null) {
			fail(getClass().getName() + ": ERRO Creating Task Repository");		
		}
		
		if(s == null) {
			fail(getClass().getName() + ": ERRO Creating Seed Repository");		
		}

		try {
			List<Task> l = taskService.getTasks(date);
			
			if (l == null) {
				fail(getClass().getName() + ": ERRO List not found");
			}
			
			assertTrue(l.size() == 4);
		} catch(Exception e) {
			fail(getClass().getName() + ": ERRO " + e.getLocalizedMessage());
		}
	}

	@Test
	public void testGetTasksUserNameStartDate() {
		if(taskService == null) {
			fail(getClass().getName() + ": ERRO Creating Task Service");		
		}
		
		if(t == null) {
			fail(getClass().getName() + ": ERRO Creating Task Repository");		
		}
		
		if(s == null) {
			fail(getClass().getName() + ": ERRO Creating Seed Repository");		
		}

		try {
			List<Task> l = taskService.getTasks("USERX1", date);
			
			if (l == null) {
				fail(getClass().getName() + ": ERRO List not found");
			}
			
			assertTrue(l.size() == 2);
		} catch(Exception e) {
			fail(getClass().getName() + ": ERRO " + e.getLocalizedMessage());
		}
	}
	
	@Test
	public void testGetTasksTaskIdStartDate() {
		if(taskService == null) {
			fail(getClass().getName() + ": ERRO Creating Task Service");		
		}
		
		if(t == null) {
			fail(getClass().getName() + ": ERRO Creating Task Repository");		
		}
		
		if(s == null) {
			fail(getClass().getName() + ": ERRO Creating Seed Repository");		
		}

		try {
			Task task = taskService.getTask(1, date);
			
			assertTrue(task != null);
		} catch(Exception e) {
			fail(getClass().getName() + ": ERRO " + e.getLocalizedMessage());
		}
	}
	
	@Test
	public void testAddTask() {
		if(taskService == null) {
			fail(getClass().getName() + ": ERRO Creating Task Service");		
		}
		
		if(t == null) {
			fail(getClass().getName() + ": ERRO Creating Task Repository");		
		}

		if(s == null) {
			fail(getClass().getName() + ": ERRO Creating Seed Repository");		
		}

		try {
			taskService.addTask(new Task(0, "USERX4", TaskStatus.PENDING, "DESC4", date, date, date), date);
			
			Task task = taskService.getTask(4, date);
			
			assertTrue(task != null);
		} catch(Exception e) {
			fail(getClass().getName() + ": ERRO " + e.getLocalizedMessage());
		}
		
		taskService.deleteTask(4, date);
	}
	
	@Test
	public void testUpdateTask() {
		if(taskService == null) {
			fail(getClass().getName() + ": ERRO Creating Task Service");		
		}
		
		if(t == null) {
			fail(getClass().getName() + ": ERRO Creating Task Repository");		
		}
		
		if(s == null) {
			fail(getClass().getName() + ": ERRO Creating Seed Repository");		
		}

		try {
			taskService.updateTask(new Task(2, "USERX---2", TaskStatus.PENDING, "DESC---3", new Date(), new Date(), new Date()), date);
			
			Task task = taskService.getTask(2, date);
			
			assertTrue(task != null && task.getUserName().equals("USERX---2") && task.getTaskDescription().equals("DESC---3"));
		} catch(Exception e) {
			fail(getClass().getName() + ": ERRO " + e.getLocalizedMessage());
		}

		t.save(new Task(2, "USERX2", TaskStatus.PENDING, "DESC3", new Date(), new Date(), new Date()));
	}
	
	@Test
	public void testDeleteTask() {
		if(taskService == null) {
			fail(getClass().getName() + ": ERRO Creating Task Service");		
		}
		
		if(t == null) {
			fail(getClass().getName() + ": ERRO Creating Task Repository");		
		}

		if(s == null) {
			fail(getClass().getName() + ": ERRO Creating Seed Repository");		
		}

		try {
			taskService.deleteTask(3, date);
			
			Task task = taskService.getTask(3, date);
			
			assertTrue(task == null);
		} catch(Exception e) {
			fail(getClass().getName() + ": ERRO " + e.getLocalizedMessage());
		}
		
		t.save(new Task(3, "USERX3", TaskStatus.PENDING, "DESC4", new Date(), new Date(), new Date()));
	}
	
	@Test
	public void testGetTaskSeed() {
		if(taskService == null) {
			fail(getClass().getName() + ": ERRO Creating Task Service");		
		}
		
		if(t == null) {
			fail(getClass().getName() + ": ERRO Creating Task Repository");		
		}
		
		if(s == null) {
			fail(getClass().getName() + ": ERRO Creating Seed Repository");		
		}

		try {
			int nSeed = taskService.getTaskSeed();
			
			Seed seed = s.findBySeedId(SeedType.TASK);
						
			assertTrue(seed.getValue() > nSeed);
		} catch(Exception e) {
			fail(getClass().getName() + ": ERRO " + e.getLocalizedMessage());
		}
	}
	
	@Test
	public void testGetHistorySeed() {
		if(taskService == null) {
			fail(getClass().getName() + ": ERRO Creating Task Service");		
		}
		
		if(t == null) {
			fail(getClass().getName() + ": ERRO Creating Task Repository");		
		}
		
		if(s == null) {
			fail(getClass().getName() + ": ERRO Creating Seed Repository");		
		}

		try {
			int nSeed = taskService.getHistorySeed();

			Seed seed = s.findBySeedId(SeedType.HISTORY);
			
			assertTrue(seed.getValue() > nSeed);
		} catch(Exception e) {
			fail(getClass().getName() + ": ERRO " + e.getLocalizedMessage());
		}
	}
}
