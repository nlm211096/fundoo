package com.bridgelabz.fundoo.repo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoo.model.Note;
import com.bridgelabz.fundoo.model.User;
@Repository
public class NoteRepoImpl implements NoteRepo {

	@Autowired
	EntityManager entityManager;
	
	
	
	@Override
	public boolean saveNote(Note note) {
		Serializable serial=null;
		Session session=entityManager.unwrap(Session.class);
		serial=session.save(note);
		
		if(serial!=null)
		{
			return true;
		}
	
		
		return false;
	}



	@Override
	public boolean deleteNote(long id) {
		Note notes=null;
		 List<Note>noteList=findAll();
		
		for(Note note:noteList)
		{
			if(id==note.getId())
			{   
				notes=new Note();
				notes.setContent(note.getContent());
				notes.setCreatedAt(note.getCreatedAt());
				notes.setId(note.getId());
				notes.setTitle(note.getTitle());
				notes.setUpdatedAt(note.getUpdatedAt());
			}
		}
		
		//Session session=entityManager.unwrap(Session.class);
		//session.delete(notes);
		//entityManager.remove(notes);
		entityManager.remove(entityManager.contains(notes) ? notes : entityManager.merge(notes));
		if(notes==null)
		{
			return false;
		}
		
		return true;
	}



	@Override
	public List<Note> findAll() {
		
			 Session session=entityManager.unwrap(Session.class);
			  Query <Note> query = session.createQuery("from  Note", Note.class);
			  List<Note>noteList=query.getResultList();
			  
		
		return noteList ;
	}



	@Override
	public boolean updateSave(long id,Note note) {
		 Session session=entityManager.unwrap(Session.class);
		
		 
				 session.merge(note);
			 
		 
		 
		return true;
	}

}
