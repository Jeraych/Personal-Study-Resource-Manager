package com.jeraych.psrm.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class NoteViewController {
  @FXML private ScrollPane notePane;
  @FXML private TextField titleField;
  @FXML private TextArea contentArea;
  @FXML private Button saveButton;

  @FXML
  public void initialize() {
    // load all notes

  }

  @FXML
  public void saveNote() {

  }
}
