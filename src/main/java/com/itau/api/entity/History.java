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

import com.itau.api.domain.OperationType;
import com.itau.api.domain.TaskStatus;

@Entity
@Table(name="History")
public class History {
	
	@Id
	@Column(name="histId")	
	int histId;
	@Column(name="userName")	
	String userName;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 16, name="status")	
	TaskStatus status;

	@Enumerated(EnumType.STRING)
	@Column(length = 16, name="operationType")	
	OperationType operationType;
	
	@Column(name="taskId")	
	int taskId;
	@Column(name="processTime")	
	Double processTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="creationDate")	
	Date creationDate;
	
	public History() {
		this.histId = 0;
		this.userName = "";
		this.status = TaskStatus.EMPTY;		
		this.operationType = OperationType.EMPTY;		
		this.taskId = 0;
		this.processTime = 0.0;
		this.creationDate = new Date();
	}
	
	public History(int histId, String userName, TaskStatus status, OperationType operationType, 
			int taskId, Double processTime, Date creationDate) {
		this.histId = histId;
		this.userName = userName;
		this.status = status;
		this.operationType = operationType;
		this.taskId = taskId;
		this.processTime = processTime;
		this.creationDate = creationDate;
	}

	public int getHistId() {
		return histId;
	}
	public String getUserName() {
		return userName;
	}
	public TaskStatus getStatus() {
		return status;
	}
	public OperationType getOperationType() {
		return operationType;
	}
	public int getTaskId() {
		return taskId;
	}
	public Double getProcessTime() {
		return processTime;
	}
	public Date getCreationDate() {
		return creationDate;
	}

	public void setHistId(int histId) {
		this.histId = histId;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setStatus(TaskStatus status) {
		this.status = status;
	}
	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public void setProcessTime(Double processTime) {
		this.processTime = processTime;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + histId;
		result = prime * result + ((processTime == null) ? 0 : processTime.hashCode());
		result = prime * result + taskId;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((operationType == null) ? 0 : operationType.hashCode());
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
		History other = (History) obj;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (histId != other.histId)
			return false;
		if (processTime == null) {
			if (other.processTime != null)
				return false;
		} else if (!processTime.equals(other.processTime))
			return false;
		if (taskId != other.taskId)
			return false;
		if (status != other.status)
			return false;
		if (operationType != other.operationType)
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
		return "History [histId=" + histId + ", userName=" + userName + ", status=" + status  + ", operationType=" + operationType +  ", taskId=" + taskId
				+ ", processTime=" + processTime + ", creationDate=" + creationDate + "]";
	}

}
