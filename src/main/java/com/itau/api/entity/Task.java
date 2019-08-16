package com.itau.api.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.itau.api.domain.TaskStatus;

@Entity
@Table(name="Task")
public class Task {
	
	@Id
	@Column(name="taskId")	
	int taskId;
	@Column(name="userName")	
	String userName;
	@Enumerated(EnumType.STRING)
	@Column(length = 16, name="status")	
	TaskStatus status;
	@Column(name="taskDescription")
	String taskDescription;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="creationDate")	
	Date creationDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dueDate")	
	Date dueDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="startAlarmDate")	
	Date startAlarmDate;
	
	public Task() {
		this.taskId = 0;
		this.userName = "";
		this.status = TaskStatus.PENDING;
		this.taskDescription = "";
		this.creationDate = new Date();
		this.dueDate = new Date();
		this.startAlarmDate = null;	
	}

	public Task(int taskId, String userName, TaskStatus status, String taskDescription, Date creationDate, Date dueDate,
			Date startAlarmDate) {
		this.taskId = taskId;
		this.userName = userName;
		this.status = status;
		this.taskDescription = taskDescription;
		this.creationDate = creationDate;
		this.dueDate = dueDate;
		this.startAlarmDate = startAlarmDate;
	}

	public int getTaskId() {
		return taskId;
	}
	public String getUserName() {
		return userName;
	}
	public TaskStatus getStatus() {
		return status;
	}
	public String getTaskDescription() {
		return taskDescription;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public Date getStartAlarmDate() {
		return startAlarmDate;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setStatus(TaskStatus status) {
		this.status = status;
	}
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public void setStartAlarmDate(Date startAlarmDate) {
		this.startAlarmDate = startAlarmDate;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((dueDate == null) ? 0 : dueDate.hashCode());
		result = prime * result + ((startAlarmDate == null) ? 0 : startAlarmDate.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((taskDescription == null) ? 0 : taskDescription.hashCode());
		result = prime * result + taskId;
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (dueDate == null) {
			if (other.dueDate != null)
				return false;
		} else if (!dueDate.equals(other.dueDate))
			return false;
		if (startAlarmDate == null) {
			if (other.startAlarmDate != null)
				return false;
		} else if (!startAlarmDate.equals(other.startAlarmDate))
			return false;
		if (status != other.status)
			return false;
		if (taskDescription == null) {
			if (other.taskDescription != null)
				return false;
		} else if (!taskDescription.equals(other.taskDescription))
			return false;
		if (taskId != other.taskId)
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", userName=" + userName + ", status=" + status + ", taskDescription="
				+ taskDescription + ", creationDate=" + creationDate + ", dueDate=" + dueDate + ", startAlarmDate="
				+ startAlarmDate + "]";
	}
}
