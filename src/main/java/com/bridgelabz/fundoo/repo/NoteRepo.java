package com.bridgelabz.fundoo.repo;

import java.util.List;

import com.bridgelabz.fundoo.model.Note;

public interface NoteRepo {
	
	public boolean saveNote(Note note);
	public List<Note>   findAll();
	public boolean deleteNote(long id);
	
	public boolean updateSave(long id,Note note) ;

}
