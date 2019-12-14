package com.bridgelabz.fundoo.model;

import java.time.LocalDateTime;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.auditing.CurrentDateTimeProvider;
import org.springframework.format.annotation.DateTimeFormat;

import com.bridgelabz.fundoo.dto.RegistrationDTO;

import lombok.Data;
@Entity
@Table
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id",unique=true ,nullable = false)
	private Long id;
	
	@Column(name = "firstname",nullable = false)
	private String firstname;
	
	@Column(name = "lastname",nullable = false)
	private String lastname;
	
	@Column(name = "email",unique=true ,nullable = false)
	private String email;
	
	@Column(name = "phone",nullable = false)
	private Long phone;
	
	@Column(name = "password",nullable = false)
	private String password;
	
	@Column(name = "isVarified")
	private Boolean isVarified;
	
	//@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "createdStamp",nullable = false)
	private LocalDateTime createdStamp = LocalDateTime.now() ;
	
	@Column(name = "updatedStamp")
	private LocalDateTime updatedStamp;
	
	
	@OneToMany(targetEntity = Note.class,cascade =CascadeType.ALL,fetch=FetchType.LAZY )
	@Column(name="id")
	private List<Note>notes;
	
	

	

}
