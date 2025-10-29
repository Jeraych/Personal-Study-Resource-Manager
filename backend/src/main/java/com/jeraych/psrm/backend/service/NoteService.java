package com.jeraych.psrm.backend.service;

import com.jeraych.psrm.backend.DTO.NoteDTO;
import com.jeraych.psrm.backend.model.Note;
import com.jeraych.psrm.backend.model.Tag;
import com.jeraych.psrm.backend.repository.NoteRepository;
import com.jeraych.psrm.backend.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoteService {
  private final NoteRepository noteRepository;
  private final TagRepository tagRepository;

  public NoteService(NoteRepository noteRepository, TagRepository tagRepository) {
    this.noteRepository = noteRepository;
    this.tagRepository = tagRepository;
  }

  public List<NoteDTO> findAllNotes() {
    List<Note> notes = noteRepository.findAll();
    List<NoteDTO> noteDtos = new ArrayList<>();
    for (Note note : notes) {
      noteDtos.add(note.toDTO(note));
    }
    return noteDtos;
  }

  public NoteDTO saveNote(Note note) {
    noteRepository.save(note);
    return note.toDTO(note);
  }

  public NoteDTO updateNoteById(long id, Note note) {
    Note update = noteRepository.findById(id);
    update.setTitle(note.getTitle());
    update.setContent(note.getContent());
    update.setTags(note.getTags());
    noteRepository.save(update);
    return note.toDTO(update);
  }

  public void deleteNoteById(long id) {
    noteRepository.deleteById(id);
  }

  public List<NoteDTO> findAllNotesByTagId(long id) {
    noteRepository.findByTagsId(id);
    List<Note> notes = noteRepository.findAll();
    List<NoteDTO> noteDtos = new ArrayList<>();
    for (Note note : notes) {
      noteDtos.add(note.toDTO(note));
    }
    return noteDtos;
  }

  public NoteDTO updateNoteWithTagId(long id, long tag_id) {
    Note update = noteRepository.findById(id);
    Tag tag = tagRepository.findById(tag_id);
    update.addTag(tag);
    noteRepository.save(update);
    return update.toDTO(update);
  }
}
