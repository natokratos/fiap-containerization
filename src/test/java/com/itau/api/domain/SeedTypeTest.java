package com.itau.api.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class SeedTypeTest {

	@Test
	public void testSize() {
		assertTrue(SeedType.values().length == 3);
	}

}
