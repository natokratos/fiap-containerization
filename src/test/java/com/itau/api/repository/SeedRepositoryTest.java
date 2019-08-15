package com.itau.api.repository;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.itau.api.domain.SeedType;
import com.itau.api.main.ItauApiTodoApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ItauApiTodoApplication.class)
public class SeedRepositoryTest {

	@Autowired
	public SeedRepository s;
	
	@Test
	public void testCreation() {
		if(s == null) {
			fail(getClass().getName() + ": ERRO Creating Seed Repository");		
		}
	}
	
	@Test
	public void testFindBySeedId() {
		if(s == null) {
			fail(getClass().getName() + ": ERRO Creating Seed Repository");		
		}
		
		try {
			assertTrue(s.findBySeedId(SeedType.TASK) != null);
		} catch(Exception e) {
			fail(getClass().getName() + ": ERRO " + e.getLocalizedMessage());			
		}		
	}
}
