package com.itau.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itau.api.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
	List<Task> findTasks();

	List<Task> findByUserName();
	
	Task findByTaskId();
}
