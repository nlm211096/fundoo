package com.bridgelabz.fundoo.response;

import lombok.Data;

@Data
public class Response {
	
	private int status;
	
	private String description;
	
	private Object data;
  
	public Response(int status, String description,Object data) {
		this.status = status;
		this.description = description;
		this.data=data;
	}
	
	public Response(int status, String description) {
		this.status = status;
		this.description = description;
		this.data=data;
	}
	

}
