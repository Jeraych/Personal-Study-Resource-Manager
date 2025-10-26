package com.jeraych.psrm.backend.controller;

import com.jeraych.psrm.backend.model.Note;
import com.jeraych.psrm.backend.repository.NoteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin
public class NoteController {
  private final NoteRepository noteRepository;

  public NoteController(NoteRepository noteRepository) {
    this.noteRepository = noteRepository;
  }

  @GetMapping
  public List<Note> findAllNotes() {
    return noteRepository.findAll();
  }

  @PostMapping
  public Note saveNote(@RequestBody Note note) {
    return noteRepository.save(note);
  }

  @PutMapping("/{id}")
  public Note updateNote(@PathVariable long id, @RequestBody Note note) {
    Note update = noteRepository.findById(id);
    update.setTitle(note.getTitle());
    update.setContent(note.getContent());
    return noteRepository.save(update);
  }
}
