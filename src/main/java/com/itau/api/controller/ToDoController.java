package com.itau.api.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	 
	@GetMapping(value = "/tasks")
	public List<Task> getTasks() {
	    return taskService.getTasks(new Date());
	}

	@GetMapping(value = "/tasks/{userName:[^0-9]+}")
	public List<Task> getTasks(@PathVariable("userName") String userName) {
	    return taskService.getTasks(userName, new Date());
	}
	
	@GetMapping(value = "/tasks/{taskId:[0-9]+}")
	public Task getTask(@PathVariable("taskId") int taskId) {
	    return taskService.getTask(taskId, new Date());
	}
	
	@PostMapping(value = "/tasks")
	public void addTask(@RequestBody Task task) {
		taskService.addTask(task, new Date());
	}
	
	@PutMapping(value = "/tasks")
	public void updateTask(@RequestBody Task task) {
		taskService.updateTask(task, new Date());
	}
	
	@DeleteMapping(value = "/tasks/{taskId:[0-9]+}")
	public void deleteTask(@PathVariable("taskId") int taskId) {
		taskService.deleteTask(taskId, new Date());
	}
}
