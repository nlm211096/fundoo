package com.bridgelabz.fundoo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestHeader;

import com.bridgelabz.fundoo.dto.AddNotesDto;
import com.bridgelabz.fundoo.model.Note;
import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.repo.NoteRepo;
import com.bridgelabz.fundoo.repo.UserRepo;
import com.bridgelabz.fundoo.util.JwtServiceProvider;

@Service
@Transactional
public class NoteServiceImpl implements NoteService {

	@Autowired
	private NoteRepo noteRepo;

	@Autowired
	private JwtServiceProvider jwtServiceProvider;

	@Autowired
	private UserRepo userRepo;

	@Override
	public Note addNotes(String token, AddNotesDto noteDto) {
		Long userId = jwtServiceProvider.parseToken(token);

		Note note = new Note();
		BeanUtils.copyProperties(noteDto, note);
		User user = userRepo.checById(userId);

		if (user != null) {
			user.getNotes().add(note);
			boolean result = noteRepo.saveNote(note);
			if (result) {
				return note;
			}

		}

		return null;

	}

	@Override
	public AddNotesDto updateNote(String token, AddNotesDto addNotesDto) {

		Note note = new Note();
		BeanUtils.copyProperties(addNotesDto, note);

		Long userId = jwtServiceProvider.parseToken(token);
		User user = userRepo.checById(userId);
		if (user != null) {
			note.setUpdatedAt(LocalDateTime.now());

			Note notes = noteRepo.updateSave(note);
			if (notes != null) {
				return addNotesDto;
			}

		}

		return null;

	}

	@Override
	public boolean deleteNote(long id) {

		boolean isDeleted = noteRepo.deleteNote(id);
		if (isDeleted) {
			return true;
		}

		return false;

	}

	@Override
	public Note checkById(long id) {
		Note notes = null;
		List<Note> list = noteRepo.findAll();
		for (Note note : list) {
			if (id == note.getNoteId()) {
				notes = new Note();
				notes.setNoteId(note.getNoteId());
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
