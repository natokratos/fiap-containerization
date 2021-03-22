package com.fiap.api.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="OrderItem")
@IdClass(OrderItemId.class)
public class OrderItem {
	
	@Id
	@Column(name="orderId")	
	long orderId;

	@Id
	@Column(name="itemId")	
	long itemId;

	@Column(name="itemDescription")	
	String itemDescription;
	
	@Column(name="itemQtty")	
	int itemQtty;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="lastUpdate")	
	Date lastUpdate;
	
	public OrderItem() {
		orderId = 0L;
		itemId = 0L;
		itemDescription = "";
		itemQtty = 0;
		lastUpdate = new Date();
	}
	
	public OrderItem(long orderId,
			long itemId, 
			String itemDescription,
			int itemQtty, 
			Date lastUpdate) {
		this.orderId = orderId;
		this.itemId = itemId;
		this.itemDescription = itemDescription;
		this.itemQtty = itemQtty;
		this.lastUpdate = lastUpdate;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public int getItemQtty() {
		return itemQtty;
	}

	public void setItemQtty(int itemQtty) {
		this.itemQtty = itemQtty;
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
		result = prime * result + ((itemDescription == null) ? 0 : itemDescription.hashCode());
		result = (int) (prime * result + itemId);
		result = prime * result + itemQtty;
		result = prime * result + ((lastUpdate == null) ? 0 : lastUpdate.hashCode());
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
		OrderItem other = (OrderItem) obj;
		if (itemDescription == null) {
			if (other.itemDescription != null)
				return false;
		} else if (!itemDescription.equals(other.itemDescription))
			return false;
		if (itemId != other.itemId)
			return false;
		if (itemQtty != other.itemQtty)
			return false;
		if (lastUpdate == null) {
			if (other.lastUpdate != null)
				return false;
		} else if (!lastUpdate.equals(other.lastUpdate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OrderItem [itemId=" + itemId + ", itemDescription=" + itemDescription + ", itemQtty=" + itemQtty
				+ ", lastUpdate=" + lastUpdate + "]";
	}


}
