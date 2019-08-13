package com.itau.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todo")
public class ToDoController {
	
	Logger logger = LoggerFactory.getLogger(ToDoController.class);
	 
	@GetMapping(value = "/tasks")
	public List<String> getTasks() {
		logger.trace("A TRACE Message");
	    logger.info("An INFO Message");
	    return new ArrayList<String>();
	}

}
