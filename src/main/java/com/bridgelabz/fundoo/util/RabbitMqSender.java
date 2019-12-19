package com.bridgelabz.fundoo.util;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RabbitMqSender implements Serializable{
	
    private static final long serialVersionUID = 1L;
	private String email;
	private String link;
	private String token;
	

	
	
}
