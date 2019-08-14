package com.itau.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itau.api.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
	public List<Task> findAll();

	public List<Task> findByUserName(String userName);
	
	public Task findByTaskId(int taskId);
}
