package com.itau.api.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itau.api.domain.OperationType;
import com.itau.api.domain.SeedType;
import com.itau.api.domain.TaskStatus;
import com.itau.api.entity.History;
import com.itau.api.entity.Seed;
import com.itau.api.entity.Task;
import com.itau.api.repository.HistoryRepository;
import com.itau.api.repository.SeedRepository;
import com.itau.api.repository.TaskRepository;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private HistoryRepository historyRepository;
	
	@Autowired
	private SeedRepository seedRepository;
	
	Logger logger = LoggerFactory.getLogger(TaskService.class);
	
	public List<Task> getTasks(Date startDate) {
		Date endDate = null;
		ArrayList<Task> taskList = null;
		
		try {
			taskList = (ArrayList<Task>) taskRepository.findAll();
			
			endDate = new Date();
			
			History history = new History(getHistorySeed(), 
					"", 
					TaskStatus.EMPTY,
					OperationType.GET,
					-1, 
					Double.valueOf(endDate.getTime() - startDate.getTime()), 
					endDate);
			
			historyRepository.save(history);			
			logger.debug("Adicionando o Historico [ " + history.toString() + " ]");
			
			if(!taskList.isEmpty()) {
				logger.info("SUCESSO Tarefas encontradas!");
			} else {
				logger.info("SUCESSO Nenhuma tarefa encontrada!");
			}
			logger.debug("SUCESSO -- [ " + taskList.toString() + " ]");
		} catch(Exception e) {
			logger.info("ERRO lendo todas as Tarefas");
			for(StackTraceElement s : e.getStackTrace()) {
				logger.error("ERRO -- [ " + s.toString() + " ]");
			}			
		}
		
		return taskList; 
	}
	
	public List<Task> getTasks(String userName, Date startDate) {
		Date endDate = null;
		ArrayList<Task> taskList = null;
		
		try {
			taskList = (ArrayList<Task>) taskRepository.findByUserName(userName);

			endDate = new Date();
			
			History history = new History(getHistorySeed(), 
					userName, 
					TaskStatus.EMPTY,
					OperationType.GET,
					-1, 
					Double.valueOf(endDate.getTime() - startDate.getTime()), 
					endDate);
			
			historyRepository.save(history);			
			logger.debug("Adicionando o Historico [ " + history.toString() + " ]");
			
			if(!taskList.isEmpty()) {
				logger.info("SUCESSO Tarefas do usuario [ " + userName + " ] encontrada!");
			} else {
				logger.info("SUCESSO Nenhuma tarefa encontrada para o usuario[ " + userName + " ]!");
			}
			logger.debug("SUCESSO -- [ " + taskList.toString() + " ]");
		} catch(Exception e) {
			logger.info("ERRO lendo todas as Tarefas");
			for(StackTraceElement s : e.getStackTrace()) {
				logger.error("ERRO -- [ " + s.toString() + " ]");
			}			
		}
		
		return taskList; 
	}
	
	public Task getTask(int taskId, Date startDate) {
		Date endDate = null;
		Task task = null;
		History history = null;
		
		try {
			task = taskRepository.findByTaskId(taskId);

			endDate = new Date();
			
			if(task != null) {
				history = new History(getHistorySeed(), 
						task.getUserName(), 
						task.getStatus(),
						OperationType.GET,
						task.getTaskId(), 
						Double.valueOf(endDate.getTime() - startDate.getTime()), 
						endDate);
				
				logger.info("SUCESSO Tarefa [ " + taskId + " ] encontrada!");
				logger.debug("SUCESSO -- [ " + task.toString() + " ]");
			} else {
				history = new History(getHistorySeed(), 
						"", 
						TaskStatus.EMPTY,
						OperationType.GET,
						taskId, 
						Double.valueOf(endDate.getTime() - startDate.getTime()), 
						endDate);
				
				logger.info("SUCESSO Nenhuma tarefa encontrada [ " + taskId + " ]!");
			}
			
			historyRepository.save(history);			
			logger.debug("Adicionando o Historico [ " + history.toString() + " ]");
		} catch(Exception e) {
			logger.info("ERRO lendo a Tarefa [ " + taskId + " ]");
			for(StackTraceElement s : e.getStackTrace()) {
				logger.error("ERRO -- [ " + s.toString() + " ]");
			}			
		}
		
		return task; 
	}
	
	@Transactional
	public void addTask(Task task, Date startDate) {
		Date endDate = null;
		
		try {
			task.setTaskId(getTaskSeed());
			
			taskRepository.save(task);
			logger.debug("Adicionando a Tarefa [ " + task.toString() + " ]");
			
			endDate = new Date();
			
			History history = new History(getHistorySeed(), 
					task.getUserName(), 
					task.getStatus(),
					OperationType.ADD,
					task.getTaskId(), 
					Double.valueOf(endDate.getTime() - startDate.getTime()), 
					endDate);
			
			historyRepository.save(history);			
			logger.debug("Adicionando o Historico [ " + history.toString() + " ]");
			
			logger.info("SUCESSO Tarefa [ " + task.getTaskDescription() + " ] adicionada!");
			logger.debug("SUCESSO -- [ " + task.toString() + " ]");
		} catch(Exception e) {
			logger.info("ERRO adicionando a Tarefa [ " + task.getTaskDescription() + " ]");
			logger.debug("ERRO -- [ " + task.toString() + " ]");
			for(StackTraceElement s : e.getStackTrace()) {
				logger.error("ERRO -- [ " + s.toString() + " ]");
			}
		}
	}

	@Transactional
	public void updateTask(Task task, Date startDate) {
		Date endDate = null;
		
		try {
			Task taskFound = taskRepository.findByTaskId(task.getTaskId());
			
			endDate = new Date();			
			
			if(taskFound != null) {
				taskRepository.save(task);
				logger.debug("Alterando a Tarefa [ " + task.toString() + " ]");
			} else {
				logger.debug("Tarefa nao encontrada [ " + task.toString() + " ]");
				
			}
						
			History history = new History(getHistorySeed(), 
					task.getUserName(), 
					task.getStatus(),
					OperationType.UPDATE,
					task.getTaskId(), 
					Double.valueOf(endDate.getTime() - startDate.getTime()), 
					endDate);
			
			historyRepository.save(history);			
			logger.debug("Adicionando o Historico [ " + history.toString() + " ]");

			if(taskFound != null) {
				logger.info("SUCESSO Tarefa [ " + task.getTaskDescription() + " ] alterada!");
				logger.debug("SUCESSO -- [ " + task.toString() + " ]");				
			} else {
				logger.info("ERRO Alterando a tarefa [ " + task.getTaskId() + " ]");
				logger.debug("ERRO -- [ " + task.toString() + " ]");				
			}
		} catch(Exception e) {
			logger.info("ERRO adicionando a Tarefa [ " + task.getTaskDescription() + " ]");
			logger.debug("ERRO -- [ " + task.toString() + " ]");
			for(StackTraceElement s : e.getStackTrace()) {
				logger.error("ERRO -- [ " + s.toString() + " ]");
			}
		}
	}
	
	@Transactional
	public void deleteTask(int taskId, Date startDate) {
		Date endDate = null;
		Task taskFound = null;
		History history = null;
		
		try {
			taskFound = taskRepository.findByTaskId(taskId);
			
			endDate = new Date();
			
			if(taskFound != null) {
				taskRepository.deleteByTaskId(taskId);
				history = new History(getHistorySeed(), 
						taskFound.getUserName(), 
						taskFound.getStatus(),
						OperationType.DELETE,
						taskFound.getTaskId(), 
						Double.valueOf(endDate.getTime() - startDate.getTime()), 
						endDate);
				logger.debug("Removendo a Tarefa [ " + taskId + " ]");
			} else {
				history = new History(getHistorySeed(), 
						"", 
						TaskStatus.EMPTY,
						OperationType.DELETE,
						taskId, 
						Double.valueOf(endDate.getTime() - startDate.getTime()), 
						endDate);				
				logger.debug("Tarefa nao encontrada [ " + taskId + " ]");
			}
			
			historyRepository.save(history);			
			logger.debug("Adicionando o Historico [ " + history.toString() + " ]");

			if(taskFound != null) {
				logger.info("SUCESSO Tarefa [ " + taskFound.getTaskDescription() + " ] removida!");
				logger.debug("SUCESSO -- [ " + taskFound.toString() + " ]");				
			} else {
				logger.info("ERRO Removendo a tarefa [ " + taskId + " ]");
			}
		} catch(Exception e) {
			logger.info("ERRO removendo a Tarefa [ " + taskId + " ]");
			for(StackTraceElement s : e.getStackTrace()) {
				logger.error("ERRO -- [ " + s.toString() + " ]");
			}
		}
	}
	
	@Transactional
	int getTaskSeed() {
		Seed seed = null;
		int nSeed = 0;
		
		seed = seedRepository.findBySeedId(SeedType.TASK);
		logger.debug("Pegando a semente para adicionar a Tarefa [ " + seed.toString() + " ]");
		
		nSeed = seed.getValue();
		seed.setValue(seed.getValue() + 1);
		
		seedRepository.save(seed);
		logger.debug("Atualizando a semente das Tarefas [ " + seed.toString() + " ]");
		
		return nSeed;
	}

	@Transactional
	int getHistorySeed() {
		Seed seed = null;
		int nSeed = 0;
		
		seed = seedRepository.findBySeedId(SeedType.HISTORY);
		logger.debug("Pegando a semente para adicionar o Historico [ " + seed.toString() + " ]");
		
		nSeed = seed.getValue();
		seed.setValue(seed.getValue() + 1);
		
		seedRepository.save(seed);
		logger.debug("Atualizando a semente dos Repositorios [ " + seed.toString() + " ]");
		
		return nSeed;
	}
}
