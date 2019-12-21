package com.bridgelabz.fundoo.service;

import com.bridgelabz.fundoo.dto.AddNotesDto;
import com.bridgelabz.fundoo.model.Note;

public interface NoteService {
	
	public Note addNotes(String token,AddNotesDto notes);
	public AddNotesDto updateNote(String  token,AddNotesDto addNotesDto);
	public boolean deleteNote(long id);
	public Note checkById(long id);
	
	
	
	

}
