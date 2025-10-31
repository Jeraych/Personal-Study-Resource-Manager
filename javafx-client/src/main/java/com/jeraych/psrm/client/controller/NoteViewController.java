package com.jeraych.psrm.client.controller;

import com.jeraych.psrm.client.model.Note;
import com.jeraych.psrm.client.model.Tag;
import com.jeraych.psrm.client.service.NoteService;
import com.jeraych.psrm.client.service.TagService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NoteViewController {
  @FXML private ListView<Note> noteListView;
  @FXML private TextField titleField;
  @FXML private TextArea contentArea;
  @FXML private Button saveButton;
  @FXML private Button newButton;
  @FXML private Button tagsButton;
  @FXML private ImageView deleteButton;
  @FXML private HBox tagContainer;

  @FXML private AnchorPane tagPane;

  private final NoteService noteService = new NoteService();
  private final TagService tagService = new TagService();
  private Note selectedNote;
  private final Set<String> selectedTags = new HashSet<>();

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

  @FXML
  public void handleTags() throws IOException {
    tagPane.setVisible(!tagPane.isVisible());
  }

  public void loadNotes() throws Exception {
    List<Note> noteList = noteService.getAllNotes();
    tagContainer.getChildren().clear();
    noteListView.setItems(FXCollections.observableArrayList(noteList));
    for (Note note : noteList) {
      for (Tag tag : tagService.findAllTags(note.getTagIds().stream().toList())) {
        addTag(tag.getTag_name(), tag.getTag_id());
      }
    }
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

  public void loadNotesByTag() throws Exception {
    List<Note> noteList = new ArrayList<>();
    if (selectedTags.isEmpty()) {
      loadNotes();
      return;
    } else {
      for (String tag : selectedTags) {
        List<Note> filteredNotes = noteService.findNotesByTagId(Long.parseLong(tag));
        filteredNotes.removeIf(noteList::contains);
        noteList.addAll(filteredNotes);
      }
    }

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

  public void addTag(String tagName, long tag_id) {
    ToggleButton tag = new ToggleButton(tagName);
    tag.setId(String.valueOf(tag_id));
    tag.setOnAction(event -> {
      if (tag.isSelected()) {
        selectedTags.add(tag.getId());
      } else {
        selectedTags.remove(tag.getId());
      }
      for (String tagId : selectedTags) {
        System.out.println(tagId);
      }
      try {
        loadNotesByTag();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
    tagContainer.getChildren().add(tag);
  }
}
