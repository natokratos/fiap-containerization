package com.itau.api.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class OperationTypeTest {

	@Test
	public void testSize() {
		assertTrue(OperationType.values().length == 5);
	}

}
