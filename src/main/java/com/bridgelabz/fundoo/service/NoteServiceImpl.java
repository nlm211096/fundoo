package com.bridgelabz.fundoo.service;



import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bridgelabz.fundoo.dto.AddNotesDto;
import com.bridgelabz.fundoo.model.Note;
import com.bridgelabz.fundoo.repo.NoteRepo;

@Service
@Transactional
public class NoteServiceImpl implements NoteService {
	
	@Autowired
	NoteRepo noteRepo;
	

	@Override
	public Note addNotes(AddNotesDto noteDto) {
		
		Note note=new Note();
		BeanUtils.copyProperties(noteDto, note);
	boolean result=noteRepo.saveNote(note);
	if(result)
	{
		return note;
	}
	return null;
		
	}

	@Override
	public boolean updateNote(long id,Note note) {
		
			boolean notes=noteRepo.updateSave(id, note);
			if(notes)
			{
				return true;
			}
		
		
	
		
		return false;
	
	}

	@Override
	public boolean deleteNote(long id) {
		
		boolean isDeleted=noteRepo.deleteNote(id);
		if(isDeleted)
		{
			return true;
		}
		
		return false;
		
	}
   


	@Override
	public Note checkById(long id) {
	    Note notes=null;
		List<Note> list=noteRepo.findAll();
		for(Note note:list)
		{
			if(id==note.getId())
			{
			 notes=new Note();
			 notes.setId(note.getId());
			 notes.setContent(note.getContent());
			 notes.setTitle(note.getTitle());
			 notes.setUpdatedAt(note.getUpdatedAt());
			 notes.setCreatedAt(note.getCreatedAt());
			 return notes;
			}
		}
		
		return notes;
	}
	
}
