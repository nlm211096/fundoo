package com.bridgelabz.fundoo.model;

import java.time.LocalDateTime;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name = "notes")
@Data
public class Note {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "noteId",unique=true ,nullable = false)
	    private Long noteId;

		@Column(name = "title")
	    private String title;

		@Column(name = "content")
	    private String content;
        
		private String color;
		private boolean isArchived;
		private boolean isPinned;
		private boolean isTrashed;
		private LocalDateTime reminedMe;
	    

		@Column(name = "createdAt")
	    private LocalDateTime createdAt;


		@Column(name = "updatedAt")
	    private LocalDateTime updatedAt;

		
	    
	
}
