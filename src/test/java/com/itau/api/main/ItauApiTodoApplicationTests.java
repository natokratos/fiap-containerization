package com.itau.api.main;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ItauApiTodoApplicationTests.class)
public class ItauApiTodoApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void start() {
		ItauApiTodoApplication.main(new String[] {});
	}
}
