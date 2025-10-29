package com.jeraych.psrm.client.model;

import java.util.HashSet;
import java.util.Set;

public class Tag {
  private long tag_id;
  private String tag_name;
  private Set<Long> noteIds =  new HashSet<>();

  public Tag() {}
  public Tag(String tag_name) {
    this.tag_name = tag_name;
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
