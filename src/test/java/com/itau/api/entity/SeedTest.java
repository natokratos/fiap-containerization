package com.itau.api.entity;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.itau.api.domain.SeedType;

@RunWith(SpringRunner.class)
public class SeedTest {

	@Test
	public void testIfCreated() {
		try {
			Seed s = new Seed();
			
			if(s.getDescription() != null && s.getDescription().isEmpty()) {
				s = new Seed(SeedType.TASK, "SEED", 2);
				if(s.getSeedId() != SeedType.TASK ||
						!s.getDescription().contentEquals("SEED") ||
						s.getValue() != 2) {
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
			Seed s = new Seed();
			
			if(s.getDescription() != null && s.getDescription().isEmpty()) {
				s.setSeedId(SeedType.TASK);
				s.setDescription("SEED");
				s.setValue(2);			
	
				if(s.getSeedId() != SeedType.TASK ||
						!s.getDescription().contentEquals("SEED") ||
						s.getValue() != 2) {
					fail(getClass().getName() + ": ERRO Dados divergentes" );
				}
			}
		} catch(Exception e) {
			fail(getClass().getName() + ": ERRO " + e.getLocalizedMessage());			
		}
	}
	
	@Test
	public void testEquals() {
		Seed s1 = new Seed(SeedType.TASK, "TASK", 1);
		Seed s2 = new Seed(SeedType.TASK, "TASK", 1);
		
		assertTrue(s1.equals(s2));
	}
	
	@Test
	public void testFailEqualsSeedId() {
		Seed s1 = new Seed(SeedType.TASK, "TASK", 1);
		Seed s2 = new Seed(SeedType.HISTORY, "TASK", 1);
		
		assertFalse(s1.equals(s2));
	}

	@Test
	public void testFailEqualsDescription1() {
		Seed s1 = new Seed(SeedType.TASK, "TASK", 1);
		Seed s2 = new Seed(SeedType.TASK, "TASK-----", 1);
		
		assertFalse(s1.equals(s2));
	}

	@Test
	public void testFailEqualsDescription2() {
		Seed s1 = new Seed(SeedType.TASK, "TASK", 1);
		Seed s3 = new Seed(SeedType.TASK, null, 1);
		
		assertFalse(s3.equals(s1));
	}
	
	@Test
	public void testFailEqualsValue() {
		Seed s1 = new Seed(SeedType.TASK, "TASK", 1);
		Seed s2 = new Seed(SeedType.TASK, "TASK", 2);
		
		assertFalse(s1.equals(s2));
	}
	@Test
	public void testHashCode() {
		Seed s1 = new Seed(SeedType.TASK, "TASK", 1);
		Seed s2 = s1;
		
		assertTrue(s1.hashCode() == s2.hashCode());
	}	
}
