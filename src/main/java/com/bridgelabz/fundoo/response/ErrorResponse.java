package com.bridgelabz.fundoo.response;

import lombok.Data;

@Data
public class ErrorResponse {
  String messages;
  public ErrorResponse (String messages) {
	  this.messages=messages;
  }
}
