package com.jeraych.psrm.client.controller;

import com.jeraych.psrm.client.model.Note;
import com.jeraych.psrm.client.service.NoteService;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class NoteViewController {
  @FXML private ListView<Note> noteListView;
  @FXML private TextField titleField;
  @FXML private TextArea contentArea;
  @FXML private Button saveButton;

  private final NoteService noteService = new NoteService();

  @FXML
  public void initialize() {
    // load all notes

  }

  @FXML
  public void handleSave() throws Exception {
    String title = titleField.getText();
    String content = contentArea.getText();
    noteService.saveNote(title, content);
  }
}
