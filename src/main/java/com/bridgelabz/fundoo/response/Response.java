package com.bridgelabz.fundoo.response;

public class Response {
	
	private int status;
	
	private String description;
    
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Response(int status, String description) {
		this.status = status;
		this.description = description;
	}
	
	

}
