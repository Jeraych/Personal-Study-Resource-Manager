package com.jeraych.psrm.client.service;

import com.jeraych.psrm.client.model.Note;

public class NoteService {

  public void saveNote(String title, String content){
    Note note = new Note(title,content);
  }
}
