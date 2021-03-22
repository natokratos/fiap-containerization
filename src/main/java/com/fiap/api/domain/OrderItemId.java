package com.fiap.api.domain;

import java.io.Serializable;

public class OrderItemId implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8524468608541866858L;

	private long orderId;
	
	private long itemId;
	
	public OrderItemId() {
	    this.orderId = 0L;
	    this.itemId = 0L;
	}
	
	public OrderItemId(long orderId, long itemId) {
	    this.orderId = orderId;
	    this.itemId = itemId;
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

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (itemId ^ (itemId >>> 32));
		result = prime * result + (int) (orderId ^ (orderId >>> 32));
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
		OrderItemId other = (OrderItemId) obj;
		if (itemId != other.itemId)
			return false;
		if (orderId != other.orderId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OrderId [orderId=" + orderId + ", itemId=" + itemId + "]";
	}
	
}
