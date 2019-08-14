package com.itau.api.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itau.api.controller.ToDoController;
import com.itau.api.domain.DomainType;
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
		
	public void addTask(Task task, Date startDate) {
		Date endDate = null;
		
		try {
			task.setTaskId(getTaskSeed());
			
			taskRepository.save(task);
			logger.debug("Adicionando a Tarefa[ " + task.toString() + " ]");
			
			endDate = new Date();
			
			History history = new History(getHistorySeed(), 
					task.getUserName(), 
					task.getStatus(), 
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
	
	int getTaskSeed() {
		Seed seed = null;
		int nSeed = 0;
		
		seed = seedRepository.findBySeedId(DomainType.TASK);
		logger.debug("Pegando a semente para adicionar a Tarefa [ " + seed.toString() + " ]");
		
		nSeed = seed.getValue();
		seed.setValue(seed.getValue() + 1);
		
		seedRepository.save(seed);
		logger.debug("Atualizando a semente das Tarefas [ " + seed.toString() + " ]");
		
		return nSeed;
	}
	
	int getHistorySeed() {
		Seed seed = null;
		int nSeed = 0;
		
		seed = seedRepository.findBySeedId(DomainType.HISTORY);
		logger.debug("Pegando a semente para adicionar o Historico [ " + seed.toString() + " ]");
		
		nSeed = seed.getValue();
		seed.setValue(seed.getValue() + 1);
		
		seedRepository.save(seed);
		logger.debug("Atualizando a semente dos Repositorios [ " + seed.toString() + " ]");
		
		return nSeed;
	}
}
