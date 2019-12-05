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

@Entity
@Table
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id",unique=true ,nullable = false)
	private long id;
	
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
	
	@Column(name = "createdStamp",nullable = false)
	private LocalDateTime createdStamp;
	
	@Column(name = "updatedStamp",nullable = false)
	private LocalDateTime updatedStamp;
	
	
	@OneToMany(targetEntity = Note.class,cascade =CascadeType.ALL,fetch=FetchType.LAZY )
	@Column(name="id")
	private List<Note>notes;
	
	
	
	public Boolean isVarified() {
		return isVarified;
	}

	public void setVarified(Boolean isVarified) {
		this.isVarified = isVarified;
	}

	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	public LocalDateTime getCreatedStamp() {
		return createdStamp;
	}

	public void setCreatedStamp(LocalDateTime createdStamp) {
		this.createdStamp = createdStamp;
	}

	public LocalDateTime getUpdatedStamp() {
		return updatedStamp;
	}

	public void setUpdatedStamp(LocalDateTime updatedStamp) {
		this.updatedStamp = updatedStamp;
	}

}
