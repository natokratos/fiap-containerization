package com.itau.api.entity;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.itau.api.domain.SeedType;
import com.itau.api.main.ItauApiTodoApplication;
import com.itau.api.repository.SeedRepository;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ItauApiTodoApplication.class)
public class HealthCheckTest {

	@Autowired
	public SeedRepository s;
	
	@Before
	public void setup() {
		s.save(new Seed(SeedType.TASK, "TASK", 4));
		s.save(new Seed(SeedType.HISTORY, "HISTORY", 0));
	}
	
	@Test
	public void testIfCreated() {
		try {
			HealthCheck h = new HealthCheck();
			
			if(h.getMethod() != null && h.getMethod().isEmpty()) {
				h = new HealthCheck("GET", "/todo/tasks", false);
				if(!h.getMethod().equals("GET") ||
						!h.getUrl().equals("/todo/tasks") ||
						h.isStatus() != false) {
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
			HealthCheck h = new HealthCheck();
			
			if(h.getMethod() != null && h.getMethod().isEmpty()) {
				h.setMethod("GET");
				h.setUrl("/todo/tasks"); 
				h.setStatus(true);
	
				if(!h.getMethod().equals("GET") ||
						!h.getUrl().equals("/todo/tasks") ||
						h.isStatus() != true) {
					fail(getClass().getName() + ": ERRO Dados divergentes" );
				}
			}
		} catch(Exception e) {
			fail(getClass().getName() + ": ERRO " + e.getLocalizedMessage());			
		}
	}
	
	@Test
	public void testEquals() {
		HealthCheck h1 = new HealthCheck("GET", "/todo/tasks", false);
		HealthCheck h2 = new HealthCheck("GET", "/todo/tasks", false);
		
		assertTrue(h1.equals(h2));
	}

	@Test
	public void testFailEqualsMethod1() {
		HealthCheck h1 = new HealthCheck("GET", "/todo/tasks", false);
		HealthCheck h2 = new HealthCheck(null, "/todo/tasks", false);
		
		assertFalse(h1.equals(h2));
	}

	@Test
	public void testFailEqualsMethod2() {
		HealthCheck h1 = new HealthCheck("GET", "/todo/tasks", false);
		HealthCheck h2 = new HealthCheck(null, "/todo/tasks", false);
		
		assertFalse(h2.equals(h1));
	}

	@Test
	public void testFailEqualsUrl1() {
		HealthCheck h1 = new HealthCheck("GET", "/todo/tasks", false);
		HealthCheck h2 = new HealthCheck("GET", null, false);
		
		assertFalse(h1.equals(h2));
	}

	@Test
	public void testFailEqualsUrl2() {
		HealthCheck h1 = new HealthCheck("GET", "/todo/tasks", false);
		HealthCheck h2 = new HealthCheck("GET", null, false);
		
		assertFalse(h2.equals(h1));
	}

	
	@Test
	public void testFailEqualsStatus() {
		HealthCheck h1 = new HealthCheck("GET", "/todo/tasks", false);
		HealthCheck h2 = new HealthCheck("GET", "/todo/tasks", true);
		
		assertFalse(h1.equals(h2));
	}

	@Test
	public void testHashCode() {
		HealthCheck h1 = new HealthCheck("GET", "/todo/tasks", false);
		HealthCheck h2 = h1;
		
		assertTrue(h1.hashCode() == h2.hashCode());
	}
}
