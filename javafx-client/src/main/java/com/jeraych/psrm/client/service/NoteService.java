package com.jeraych.psrm.client.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeraych.psrm.client.model.Note;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class NoteService {

  private static final String BASE_URL = "http://localhost:8080/api/notes";

  public void saveNote(String title, String content) throws Exception {
    Note note = new Note(title,content);
    ObjectMapper objectMapper = new ObjectMapper();
    String json = objectMapper.writeValueAsString(note);
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(BASE_URL))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(json))
            .build();
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
  }
}
