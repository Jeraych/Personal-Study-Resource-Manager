package com.jeraych.psrm.backend.DTO;

import com.jeraych.psrm.backend.model.Note;
import com.jeraych.psrm.backend.model.Tag;

import java.util.Set;
import java.util.stream.Collectors;

public class TagDTO {
  private long tag_id;
  private String tag_name;
  private Set<Long> noteIds;
  public TagDTO() {
  }

  public long getTag_id() {
    return tag_id;
  }

  public void setTag_id(long tag_id) {
    this.tag_id = tag_id;
  }

  public String getTag_name() {
    return tag_name;
  }

  public void setTag_name(String tag_name) {
    this.tag_name = tag_name;
  }

  public Set<Long> getNoteIds() {
    return noteIds;
  }

  public void setNoteIds(Set<Long> noteIds) {
    this.noteIds = noteIds;
  }
}
