package com.jeraych.psrm.backend.service;

import com.jeraych.psrm.backend.DTO.NoteDTO;
import com.jeraych.psrm.backend.model.Note;
import com.jeraych.psrm.backend.model.Tag;
import com.jeraych.psrm.backend.repository.NoteRepository;
import com.jeraych.psrm.backend.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
      noteDtos.add(Note.toDTO(note));
    }
    return noteDtos;
  }

  public NoteDTO saveNote(NoteDTO note) {
    Note noteEntity = Note.toEntity(note);
    for (Long tagId : note.getTagIds()) {
      Optional<Tag> tagEntity = tagRepository.findById(tagId);
      tagEntity.ifPresent(noteEntity::addTag);
    }
    noteRepository.save(noteEntity);
    return Note.toDTO(noteEntity);
  }

  public NoteDTO updateNoteById(long id, NoteDTO note) {
    Note update = noteRepository.findById(id);
    update.setTitle(note.getNote_title());
    update.setContent(note.getNote_content());
    for (Long tagId : note.getTagIds()) {
      Optional<Tag> tagEntity = tagRepository.findById(tagId);
      tagEntity.ifPresent(update::addTag);
    }
    noteRepository.save(update);
    return Note.toDTO(update);
  }

  public void deleteNoteById(long id) {
    noteRepository.deleteById(id);
  }

  public List<NoteDTO> findAllNotesByTagId(long id) {
    List<Note> notes = noteRepository.findByTagsId(id);
    List<NoteDTO> noteDtos = new ArrayList<>();
    for (Note note : notes) {
      noteDtos.add(Note.toDTO(note));
    }
    return noteDtos;
  }

  public NoteDTO addTagToNote(long id, long tag_id) {
    Note update = noteRepository.findById(id);
    Tag tag = tagRepository.findById(tag_id);
    update.addTag(tag);
    noteRepository.save(update);
    return Note.toDTO(update);
  }

  public List<NoteDTO> getNotesByIds(List<Long> id) {
    return noteRepository.findAllById(id).stream()
            .map(Note::toDTO)
            .collect(Collectors.toList());
  }
}
