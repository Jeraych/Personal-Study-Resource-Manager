package com.jeraych.psrm.backend.DTO;

import java.util.Set;

public class NoteDTO {
  private long note_id;
  private String note_title;
  private String note_content;
  private Set<Long> tagIds;

  public NoteDTO() {}

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
}
