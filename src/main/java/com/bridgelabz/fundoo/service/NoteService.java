package com.bridgelabz.fundoo.service;

import com.bridgelabz.fundoo.dto.AddNotesDto;
import com.bridgelabz.fundoo.model.Note;

public interface NoteService {
	
	public Note addNotes(AddNotesDto notes);
	public boolean updateNote(long id,Note note);
	public boolean deleteNote(long id);
	public Note checkById(long id);
	
	
	

}
