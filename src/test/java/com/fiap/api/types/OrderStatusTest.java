package com.fiap.api.types;

import static org.junit.Assert.*;

import org.junit.Test;

import com.fiap.api.types.OrderStatus;

public class OrderStatusTest {

	@Test
	public void testSize() {
		assertTrue(OrderStatus.values().length == 6);
	}
}
