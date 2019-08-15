package com.itau.api.entity;

public class HealthCheck {
	String method;
	String url;
	boolean status;
	
	public HealthCheck() {
		this.method = "";
		this.url = "";
		this.status = false;
	}

	public HealthCheck(String method, String url, boolean status) {
		this.method = method;
		this.url = url;
		this.status = status;
	}
	
	public String getMethod() {
		return method;
	}
	public String getUrl() {
		return url;
	}
	public boolean isStatus() {
		return status;
	}

	public void setMethod(String method) {
		this.method = method;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((method == null) ? 0 : method.hashCode());
		result = prime * result + (status ? 1231 : 1237);
		result = prime * result + ((url == null) ? 0 : url.hashCode());
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
		HealthCheck other = (HealthCheck) obj;
		if (method == null) {
			if (other.method != null)
				return false;
		} else if (!method.equals(other.method))
			return false;
		if (status != other.status)
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "HealthCheck [method=" + method + ", url=" + url + ", status=" + status + "]";
	}

}
