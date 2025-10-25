package com.jeraych.psrm.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

  @Override
  public void start(Stage stage) throws Exception {
    // Load FXML from resources
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/NoteView.fxml"));
    Parent root = loader.load();

    // Create a scene with the root node
    Scene scene = new Scene(root);

    // Set scene to stage and show
    stage.setScene(scene);
    stage.setTitle("Personal Study Resource Manager");
    stage.show();
  }

  public static void main(String[] args) {launch(args);}
}