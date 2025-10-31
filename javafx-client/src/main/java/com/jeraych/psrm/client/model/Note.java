package com.jeraych.psrm.client.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Note {
  private long note_id;
  private String note_title;
  private String note_content;
  private Set<Long> tagIds =  new HashSet<>();

  public Note() {}
  public Note(String note_title, String note_content) {
    this.note_title = note_title;
    this.note_content = note_content;
  }

  public long getNote_id() {
    return note_id;
  }

  public void setNote_id(long note_id) {
    this.note_id = note_id;
  }

  public String getNote_title() {
    return note_title;
  }

  public void setNote_title(String note_title) {
    this.note_title = note_title;
  }

  public String getNote_content() {
    return note_content;
  }

  public void setNote_content(String note_content) {
    this.note_content = note_content;
  }

  public Set<Long> getTagIds() {
    return tagIds;
  }

  public void setTagIds(Set<Long> tagIds) {
    this.tagIds = tagIds;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Note note)) return false;
    return Objects.equals(note_id, note.note_id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(note_id);
  }
}
