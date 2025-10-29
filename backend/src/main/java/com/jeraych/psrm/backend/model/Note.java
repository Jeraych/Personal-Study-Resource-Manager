package com.jeraych.psrm.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.jeraych.psrm.backend.DTO.NoteDTO;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Note {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String title;
  private String content;

  @ManyToMany(cascade = {CascadeType.MERGE ,CascadeType.PERSIST})
  @JoinTable(
          name = "note_tag",
          joinColumns = @JoinColumn(name = "note_id"),
          inverseJoinColumns = @JoinColumn(name = "tag_id")
  )
  private Set<Tag> tags = new HashSet<>();

  public Note(String title, String content) {
    this.title = title;
    this.content = content;
  }

  public Note() {}

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Set<Tag> getTags() {
    return tags;
  }

  public void setTags(Set<Tag> tags) {
    this.tags = tags;
  }

  public void addTag(Tag tag) {
    this.tags.add(tag);
    tag.getNotes().add(this);
  }

  public void removeTag(Tag tag) {
    this.tags.remove(tag);
    tag.getNotes().remove(this);
  }

  public static NoteDTO toDTO(Note note) {
    NoteDTO dto = new NoteDTO();
    dto.setNote_id(note.getId());
    dto.setNote_title(note.getTitle());
    dto.setNote_content(note.getContent());
    dto.setTagIds(note.getTags().stream().map(Tag::getId).collect(Collectors.toSet()));
    return dto;
  }
}
