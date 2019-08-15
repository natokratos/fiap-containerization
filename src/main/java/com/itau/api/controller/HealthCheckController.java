package com.itau.api.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itau.api.domain.TaskStatus;
import com.itau.api.entity.HealthCheck;
import com.itau.api.entity.Task;

@RestController
@RequestMapping("/")
public class HealthCheckController {
	
	@Autowired
	ToDoController toDoController;
	
	Logger logger = LoggerFactory.getLogger(ToDoController.class);
	
	@GetMapping("/healthcheck")
	public List<HealthCheck> doHealthCheck() {
		Date date = new Date();
		List<Task> list = null;
		ArrayList<HealthCheck> result = new ArrayList<HealthCheck>();
		int nTasks = 0;
		Task task = null;
		
		//Checking GET /todo/tasks
		list = toDoController.getTasks();
		if(list != null) {
			nTasks = list.size();
			result.add(new HealthCheck("GET", "/todo/tasks", true));
			logger.info("[  OK  ]  GET  /todo/tasks");
		} else {
			result.add(new HealthCheck("GET", "/todo/tasks", false));
			logger.info("[ ERRO ]  GET  /todo/tasks");
		}

		//Checking POST /todo/tasks
		toDoController.addTask(new Task(0, "USERX4", TaskStatus.PENDING, "DESC4", date, date, date));
		if(toDoController.getTasks().size() > nTasks) {
			result.add(new HealthCheck("POST", "/todo/tasks", true));
			logger.info("[  OK  ]  POST  /todo/tasks");
		} else {
			result.add(new HealthCheck("POST", "/todo/tasks", false));
			logger.info("[ ERRO ] POST  /todo/tasks");
		}
		
		//Checking GET /todo/tasks/{userName}
		list = toDoController.getTasks("USERX4");
		if(list != null) {
			result.add(new HealthCheck("GET", "/todo/tasks/{userName}", true));
			logger.info("[  OK  ]  GET  /todo/tasks/{userName}");
			
		} else {
			result.add(new HealthCheck("GET", "/todo/tasks/{userName}", false));
			logger.info("[ ERRO ]  GET  /todo/tasks/{userName}");
		}
		
		//Checking PUT /todo/tasks
		list.get(0).setTaskDescription("XXXXX");
		
		toDoController.updateTask(list.get(0));
		
		list = toDoController.getTasks("USERX4");
		if(list != null && list.get(0).getTaskDescription().equals("XXXXX")) {
			nTasks = list.size();
			result.add(new HealthCheck("PUT", "/todo/tasks", true));
			logger.info("[  OK  ]  PUT  /todo/tasks");
		} else {
			result.add(new HealthCheck("PUT", "/todo/tasks", false));
			logger.info("[ ERRO ]  PUT  /todo/tasks");
		}

		//Checking GET /todo/tasks/{taskId}
		task = toDoController.getTask(list.get(0).getTaskId());
		if(task != null) {
			result.add(new HealthCheck("GET", "/todo/tasks/{taskId}", true));
			logger.info("[  OK  ]  GET  /todo/tasks/{taskId}");
		} else {
			result.add(new HealthCheck("GET", "/todo/tasks/{taskId}", false));
			logger.info("[ ERRO ]  GET  /todo/tasks/{taskId}");
		}		

		//Checking DELETE /todo/tasks/{taskId}
		toDoController.deleteTask(list.get(0).getTaskId());
		
		task = toDoController.getTask(list.get(0).getTaskId());
		if(task == null) {
			result.add(new HealthCheck("DELETE", "/todo/tasks/{taskId}", true));
			logger.info("[  OK  ] DELETE /todo/tasks/{taskId}");
		} else {
			result.add(new HealthCheck("DELETE", "/todo/tasks/{taskId}", false));
			logger.info("[ ERRO ] DELETE /todo/tasks/{taskId}");
		}
		
		return result;
	}

}
