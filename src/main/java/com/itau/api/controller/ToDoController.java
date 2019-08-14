package com.itau.api.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jws.WebParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itau.api.entity.Task;
import com.itau.api.service.TaskService;

@RestController
@RequestMapping("/todo")
public class ToDoController {
	
	@Autowired
	TaskService taskService;
	
	Logger logger = LoggerFactory.getLogger(ToDoController.class);
	 
	@GetMapping(value = "/tasks")
	public List<String> getTasks() {
		logger.trace("A TRACE Message");
	    logger.info("An INFO Message");
	    return new ArrayList<String>();
	}

	@PostMapping(value = "/tasks")
	public void addTask(@RequestBody Task task) {
		taskService.addTask(task, new Date());
	}
}
