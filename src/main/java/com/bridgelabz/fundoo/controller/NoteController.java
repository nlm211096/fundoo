package com.bridgelabz.fundoo.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.dto.AddNotesDto;
import com.bridgelabz.fundoo.model.Note;
import com.bridgelabz.fundoo.response.Response;
import com.bridgelabz.fundoo.service.NoteService;

@RestController
@RequestMapping("/note")
public class NoteController {
	
	@Autowired
	NoteService noteService;
	
	
	
	
	
	@PostMapping("/addnotes")
	public ResponseEntity<Response> getAllNotes(@Valid @RequestBody AddNotesDto notes)
	{
		
		Note note=noteService.addNotes(notes);
		if(note!=null)
		{
			
			return new ResponseEntity<Response>( new Response(HttpStatus.OK.value(), "adding notes success"),HttpStatus.OK);
		}	
		
		
		
		return new ResponseEntity<Response>( new Response(HttpStatus.BAD_REQUEST.value(),"not success"),HttpStatus.BAD_REQUEST);

	}
	

	@GetMapping("/deleteNotes/{id}")
	public ResponseEntity<Response> deleteNotes(@PathVariable(name="id") long id)
	{ 
		boolean result=noteService.deleteNote(id);
      if(result)
      {
    	  return new ResponseEntity<Response>( new Response(HttpStatus.OK.value(), "deletion success"),HttpStatus.OK);
  		  
      }
		
      return new ResponseEntity<Response>( new Response(HttpStatus.BAD_REQUEST.value(),"please enter correct id"),HttpStatus.BAD_REQUEST);
 
	}
	
	@GetMapping("/update/{id}")
	public ResponseEntity<Response> updateNote(@PathVariable(name="id") long id,@RequestBody Note noteDetails )
	{
		Note note=noteService.checkById(id);
		if(note!=null)
		{
			note.setContent(noteDetails.getContent());
			note.setTitle(noteDetails.getTitle());
			
		    boolean isUpdated=noteService.updateNote(note);
			if(isUpdated)
			{
				return new ResponseEntity<Response>( new Response(HttpStatus.OK.value(), "updation  success"),HttpStatus.OK);
		  		  
			}
		}
		
		return new ResponseEntity<Response>( new Response(HttpStatus.BAD_REQUEST.value(),"id not existed"),HttpStatus.BAD_REQUEST);
		 
		   
	}

}
