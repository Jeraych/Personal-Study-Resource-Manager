package com.jeraych.psrm.backend.controller;

import com.jeraych.psrm.backend.DTO.NoteDTO;
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

  @PostMapping
  public NoteDTO createNote(@RequestBody Note note) {
    return noteService.saveNote(note);
  }

  @PutMapping("/{id}")
  public NoteDTO updateNote(@PathVariable long id, @RequestBody Note note) {return noteService.updateNoteById(id, note);}

  @DeleteMapping("/{id}")
  public void deleteNote(@PathVariable long id) {
    noteService.deleteNoteById(id);
  }

  @GetMapping("/{tag_id}")
  public List<NoteDTO> findByTagId(@PathVariable long tag_id) { return noteService.findAllNotesByTagId(tag_id); }

  @PostMapping("/{id}/{tag_id}")
  public NoteDTO updateNoteWithTag(@PathVariable long id, @PathVariable long tag_id) {
    return noteService.addTagToNote(id, tag_id);
  }

  @GetMapping
  public List<NoteDTO> findAllNotesByIds(@RequestParam(required = false) List<Long> id) {
    if (id == null) {
      return noteService.findAllNotes();
    }
    return noteService.getNotesByIds(id);
  }
}
