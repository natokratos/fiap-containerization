package com.itau.api.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class TaskStatusTest {

	@Test
	public void testSize() {
		assertTrue(TaskStatus.values().length == 3);
	}
}
