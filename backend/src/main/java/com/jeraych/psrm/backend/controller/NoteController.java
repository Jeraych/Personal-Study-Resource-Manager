package com.jeraych.psrm.backend.controller;

import com.jeraych.psrm.backend.model.Note;
import com.jeraych.psrm.backend.service.NoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {
  private final NoteService noteService;

  public NoteController(NoteService noteService) {
    this.noteService = noteService;
  }

  @GetMapping
  public List<Note> getAllNotes() {
    return noteService.findAllNotes();
  }

  @PostMapping
  public Note createNote(@RequestBody Note note) {
    return noteService.saveNote(note);
  }

  @PutMapping("/{id}")
  public Note updateNote(@PathVariable long id, @RequestBody Note note) {
    return noteService.updateNoteById(id, note);
  }

  @DeleteMapping("/{id}")
  public void deleteNote(@PathVariable long id) {
    noteService.deleteNoteById(id);
  }

  @GetMapping("/{tag_id}")
  public List<Note> findByTagId(@PathVariable long tag_id) { return noteService.findAllNotesByTagId(tag_id); }
}
