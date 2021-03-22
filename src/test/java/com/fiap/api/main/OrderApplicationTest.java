package com.fiap.api.main;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fiap.api.main.OrderApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderApplicationTest.class)
public class OrderApplicationTest {

	@Test
	public void contextLoads() {
	}

	@Test
	public void start() {
		OrderApplication.main(new String[] {});
	}
}
