package com.bridgelabz.fundoo.repo;

import java.util.List;

import com.bridgelabz.fundoo.dto.AddNotesDto;
import com.bridgelabz.fundoo.model.Note;

public interface NoteRepo {
	
	public boolean saveNote(Note note);
	public List<Note>   findAll();
	public boolean deleteNote(long id);
	Note updateSave(Note note);
	
	
}
