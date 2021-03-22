package com.fiap.api.types;

import static org.junit.Assert.*;

import org.junit.Test;

import com.fiap.api.types.PaymentType;

public class PaymentTypeTest {

	@Test
	public void testSize() {
		assertTrue(PaymentType.values().length == 5);
	}
}
