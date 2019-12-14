package com.bridgelabz.fundoo.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class AddNotesDto {
	
	    @NotBlank
	    private String title;

	    @NotBlank
	    private String content;

		

}
