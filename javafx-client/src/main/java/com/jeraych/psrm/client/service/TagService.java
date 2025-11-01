package com.jeraych.psrm.client.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeraych.psrm.client.model.Note;
import com.jeraych.psrm.client.model.Tag;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class TagService {
  private static final String BASE_URL = "http://localhost:8080/api/tags";

  public Tag findTag(long id) throws Exception {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(BASE_URL + "?id=" + id))
            .GET()
            .build();
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.readValue(response.body(), Tag.class);
  }

  public List<Tag> findAllTags(List<Long> id) throws Exception {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < id.size(); i++) {
      sb.append(id.get(i));
      if (i < id.size() - 1) {
        sb.append(",");
      }
    }
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(BASE_URL + "?id=" + sb.toString()))
            .GET()
            .build();
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.readValue(response.body(), new TypeReference<List<Tag>>() {});
  }

  public void saveTag(String tagName) throws Exception {
    Tag tag = new Tag(tagName);
    ObjectMapper objectMapper = new ObjectMapper();
    String json = objectMapper.writeValueAsString(tag);
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(BASE_URL))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(json))
            .build();
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
  }

  public void deleteTag(long id) throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(BASE_URL + "/" + id))
            .header("Content-Type", "application/json")
            .DELETE()
            .build();
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
  }

}
