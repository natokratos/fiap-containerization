package com.fiap.api.domain;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.fiap.api.domain.Seed;
import com.fiap.api.types.SeedType;

@RunWith(SpringRunner.class)
public class SeedTest {

	@Test
	public void testIfCreated() {
		try {
			Seed s = new Seed();
			
			if(s.getDescription() != null && s.getDescription().isEmpty()) {
				s = new Seed(SeedType.ORDER, "ORDER", 2);
				if(s.getSeedId() != SeedType.ORDER ||
						!s.getDescription().contentEquals("ORDER") ||
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
				s.setSeedId(SeedType.ORDER);
				s.setDescription("ORDER");
				s.setValue(2);			
	
				if(s.getSeedId() != SeedType.ORDER ||
						!s.getDescription().contentEquals("ORDER") ||
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
		Seed s1 = new Seed(SeedType.ORDER, "ORDER", 1);
		Seed s2 = new Seed(SeedType.ORDER, "ORDER", 1);
		
		assertTrue(s1.equals(s2));
	}
	
	@Test
	public void testFailEqualsSeedId() {
		Seed s1 = new Seed(SeedType.ORDER, "ORDER", 1);
		Seed s2 = new Seed(SeedType.EMPTY, "ORDER", 1);
		
		assertFalse(s1.equals(s2));
	}

	@Test
	public void testFailEqualsDescription1() {
		Seed s1 = new Seed(SeedType.ORDER, "ORDER", 1);
		Seed s2 = new Seed(SeedType.ORDER, "ORDER-----", 1);
		
		assertFalse(s1.equals(s2));
	}

	@Test
	public void testFailEqualsDescription2() {
		Seed s1 = new Seed(SeedType.ORDER, "ORDER", 1);
		Seed s3 = new Seed(SeedType.ORDER, null, 1);
		
		assertFalse(s3.equals(s1));
	}
	
	@Test
	public void testFailEqualsValue() {
		Seed s1 = new Seed(SeedType.ORDER, "ORDER", 1);
		Seed s2 = new Seed(SeedType.ORDER, "ORDER", 2);
		
		assertFalse(s1.equals(s2));
	}
	@Test
	public void testHashCode() {
		Seed s1 = new Seed(SeedType.ORDER, "ORDER", 1);
		Seed s2 = s1;
		
		assertTrue(s1.hashCode() == s2.hashCode());
	}	
}
