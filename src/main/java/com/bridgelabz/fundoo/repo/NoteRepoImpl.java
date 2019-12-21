package com.bridgelabz.fundoo.repo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoo.model.Note;
import com.bridgelabz.fundoo.model.User;

@Repository
public class NoteRepoImpl implements NoteRepo {

	@Autowired
	private EntityManager entityManager;

	@Override
	public boolean saveNote(Note note) {
		Serializable serial = null;
		Session session = entityManager.unwrap(Session.class);
		serial = session.save(note);

		if (serial != null) {
			return true;
		}

		return false;
	}

	@Transactional
	@Override
	public boolean deleteNote(long id) {
		Note notes = null;
		List<Note> noteList = findAll();

		for (Note note : noteList) {
			if (id == note.getNoteId()) {
				notes = new Note();
				notes.setContent(note.getContent());
				notes.setCreatedAt(note.getCreatedAt());
				notes.setNoteId(note.getNoteId());
				notes.setTitle(note.getTitle());
				notes.setUpdatedAt(note.getUpdatedAt());
			}
		}

		// Session session=entityManager.unwrap(Session.class);
		// session.delete(notes);
		// entityManager.remove(notes);
		entityManager.remove(entityManager.contains(notes) ? notes : entityManager.merge(notes));
		if (notes == null) {
			return false;
		}

		return true;
	}

	@Override
	public List<Note> findAll() {

		Session session = entityManager.unwrap(Session.class);
		Query<Note> query = session.createQuery("from  Note", Note.class);
		List<Note> noteList = query.getResultList();

		return noteList;
	}

	@Override
	public Note updateSave(Note note) {
		Session session = entityManager.unwrap(Session.class);

		Note notes = (Note) session.merge(note);

		return notes;
	}

}
