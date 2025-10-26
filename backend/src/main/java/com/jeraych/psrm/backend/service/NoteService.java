package com.jeraych.psrm.backend.service;

import com.jeraych.psrm.backend.model.Note;
import com.jeraych.psrm.backend.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
  private final NoteRepository noteRepository;

  public NoteService(NoteRepository noteRepository) {
    this.noteRepository = noteRepository;
  }

  public List<Note> findAllNotes() {
    return noteRepository.findAll();
  }

  public Note saveNote(Note note) {
    return noteRepository.save(note);
  }

  public Note updateNoteById(long id, Note note) {
    Note update = noteRepository.findById(id);
    update.setTitle(note.getTitle());
    update.setContent(note.getContent());
    return noteRepository.save(update);
  }

  public void deleteNoteById(long id) {
    noteRepository.deleteById(id);
  }
}
