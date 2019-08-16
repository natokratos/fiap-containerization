package com.itau.api.repository;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.itau.api.domain.SeedType;
import com.itau.api.entity.Seed;
import com.itau.api.main.ItauApiTodoApplication;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ItauApiTodoApplication.class)
public class SeedRepositoryTest {

	@Autowired
	public SeedRepository s;

	@Before
	public void setup() {
		s.save(new Seed(SeedType.TASK, "TASK", 4));
		s.save(new Seed(SeedType.HISTORY, "HISTORY", 0));
	}
	
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
