package com.itau.api.controller;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.itau.api.entity.HealthCheck;
import com.itau.api.main.ItauApiTodoApplication;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ItauApiTodoApplication.class)
public class HealthCheckControllerTest {
	
	@Autowired
	public HealthCheckController healthCheckController;
	
	@Test
	public void testCreation() {
		if(healthCheckController == null) {
			fail(getClass().getName() + ": ERRO Creating HealthCheck Controller");		
		}
	}
	
	@Test
	public void testDoHealthCheck() {
		if(healthCheckController == null) {
			fail(getClass().getName() + ": ERRO Creating HealthCheck Controller");		
		}
		
		try {
			List<HealthCheck> l = healthCheckController.doHealthCheck();
			
			assertTrue(l != null && l.size() == 6);
		} catch(Exception e) {
			fail(getClass().getName() + ": ERRO " + e.getLocalizedMessage());
		}
	}

}
