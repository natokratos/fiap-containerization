package com.fiap.api.repository;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.fiap.api.domain.Seed;
import com.fiap.api.main.OrderApplication;
import com.fiap.api.repository.SeedRepository;
import com.fiap.api.types.SeedType;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderApplication.class)
public class SeedRepositoryTest {

	@Autowired
	public SeedRepository s;

	@Before
	public void setup() {
		s.save(new Seed(SeedType.ORDER, "ORDER", 4));
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
			assertTrue(s.findBySeedId(SeedType.ORDER) != null);
		} catch(Exception e) {
			fail(getClass().getName() + ": ERRO " + e.getLocalizedMessage());			
		}		
	}
}
