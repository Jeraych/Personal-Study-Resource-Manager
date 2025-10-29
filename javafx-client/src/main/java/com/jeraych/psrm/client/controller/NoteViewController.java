package com.jeraych.psrm.client.controller;

import com.jeraych.psrm.client.model.Note;
import com.jeraych.psrm.client.service.NoteService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.List;

public class NoteViewController {
  @FXML private ListView<Note> noteListView;
  @FXML private TextField titleField;
  @FXML private TextArea contentArea;
  @FXML private Button saveButton;
  @FXML private Button newButton;
  @FXML private Button deleteButton;

  private final NoteService noteService = new NoteService();
  private Note selectedNote;

  @FXML
  public void initialize() throws Exception {
    // initialize note list
    loadNotes();
  }

  @FXML
  public void handleNew() {
    selectedNote = null;
    deleteButton.setVisible(false);
    clearFields();
    noteListView.getSelectionModel().clearSelection();
  }

  @FXML
  public void handleSave() throws Exception {
    if (selectedNote == null) {
      // save a new note
      String title = titleField.getText();
      String content = contentArea.getText();
      noteService.saveNote(title, content);
      loadNotes();
      noteListView.getSelectionModel().selectLast();
      selectedNote = noteListView.getSelectionModel().getSelectedItem();
      deleteButton.setVisible(true);
    } else {
      // edit a current note
      int selectedIndex = 0;
      String title = titleField.getText();
      String content = contentArea.getText();
      noteService.editNote(selectedNote.getNote_id(), title, content);
      selectedIndex = noteListView.getSelectionModel().getSelectedIndex();
      loadNotes();
      noteListView.getSelectionModel().select(selectedIndex);
    }
  }

  @FXML
  public void handleDelete() throws Exception {
    noteService.deleteNote(selectedNote);
    selectedNote = null;
    clearFields();
    deleteButton.setVisible(false);
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
          setText(item.getNote_title());
          setOnMouseClicked(event -> {
            selectedNote = item;
            loadNote(item);
          });
        }
      }
    });
  }

  public void loadNote(Note note) {
    titleField.setText(note.getNote_title());
    contentArea.setText(note.getNote_content());
    deleteButton.setVisible(true);
  }

  public void clearFields() {
    titleField.clear();
    contentArea.clear();
  }
}
