package com.jeraych.psrm.client.controller;

import com.jeraych.psrm.client.model.Note;
import com.jeraych.psrm.client.service.NoteService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

public class NoteViewController {
  @FXML private ListView<Note> noteListView;
  @FXML private TextField titleField;
  @FXML private TextArea contentArea;
  @FXML private Button saveButton;

  private final NoteService noteService = new NoteService();
  private Note selectedNote;

  @FXML
  public void initialize() throws Exception {
    // initialize note list
    loadNotes();
  }

  @FXML
  public void handleSave() throws Exception {
    if (selectedNote == null) {
      String title = titleField.getText();
      String content = contentArea.getText();
      noteService.saveNote(title, content);
    } else {
      noteService.editNote(selectedNote);
    }
    loadNotes();
  }

  public void loadNotes() throws Exception {
    List<Note> noteList = noteService.getAllNotes();
    noteListView.setItems(FXCollections.observableArrayList(noteList));
    noteListView.setCellFactory(listView -> new ListCell<>() {
      @Override
      protected void updateItem(Note item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
          setText(null);
        } else {
          setText(item.getTitle());
          setOnMouseClicked(event -> {
            selectedNote = item;
            loadNote(item);
          });
        }
      }
    });
  }

  public void loadNote(Note note) {
    titleField.setText(note.getTitle());
    contentArea.setText(note.getContent());
  }
}
