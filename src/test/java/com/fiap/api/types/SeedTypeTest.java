package com.fiap.api.types;

import static org.junit.Assert.*;

import org.junit.Test;

import com.fiap.api.types.SeedType;

public class SeedTypeTest {

	@Test
	public void testSize() {
		assertTrue(SeedType.values().length == 3);
	}

}
