package com.fiap.api.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fiap.api.types.PaymentType;
import com.fiap.api.types.OrderStatus;

@Entity
@Table(name="Orders")
public class Order {
	
	@Id
	@Column(name="orderId")	
	long orderId;
	
	@Column(name="userName")	
	String userName;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 16, name="status")	
	OrderStatus status;

	@Enumerated(EnumType.STRING)
	@Column(length = 16, name="paymentType")	
	PaymentType paymentType;
	
	@Column(name="totalQtty")	
	int totalQtty;

	@Column(name="totalValue")	
	double totalValue;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="lastUpdate")	
	Date lastUpdate;
	
	public Order() {
		orderId = 0L;
		userName = "";
		status = OrderStatus.EMPTY;
		paymentType = PaymentType.EMPTY;
		totalQtty = 0;
		totalValue = 0.0;
		lastUpdate = new Date();
	}
	
	public Order(long orderId,  
			String userName,
			OrderStatus status, 
			PaymentType paymentType, 
			int totalQtty, 
			double totalValue, 
			Date lastUpdate) {
		this.orderId = orderId;
		this.userName = userName;
		this.status = status;
		this.paymentType = paymentType;
		this.totalQtty = totalQtty;
		this.totalValue = totalValue;
		this.lastUpdate = lastUpdate;
	}

	
	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public int getTotalQtty() {
		return totalQtty;
	}

	public void setTotalQtty(int totalQtty) {
		this.totalQtty = totalQtty;
	}

	public double getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(double totalValue) {
		this.totalValue = totalValue;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lastUpdate == null) ? 0 : lastUpdate.hashCode());
		result = prime * result + (int) (orderId ^ (orderId >>> 32));
		result = prime * result + ((paymentType == null) ? 0 : paymentType.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + totalQtty;
		long temp;
		temp = Double.doubleToLongBits(totalValue);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Order other = (Order) obj;
		if (lastUpdate == null) {
			if (other.lastUpdate != null)
				return false;
		} else if (!lastUpdate.equals(other.lastUpdate))
			return false;
		if (orderId != other.orderId)
			return false;
		if (paymentType != other.paymentType)
			return false;
		if (status != other.status)
			return false;
		if (totalQtty != other.totalQtty)
			return false;
		if (Double.doubleToLongBits(totalValue) != Double.doubleToLongBits(other.totalValue))
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
		return "Order [orderId=" + orderId + ", userName=" + userName + ", status=" + status
				+ ", paymentType=" + paymentType + ", totalQtty=" + totalQtty + ", totalValue=" + totalValue
				+ ", lastUpdate=" + lastUpdate + "]";
	}

}
