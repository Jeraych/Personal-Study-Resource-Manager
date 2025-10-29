package com.jeraych.psrm.backend.service;

import com.jeraych.psrm.backend.DTO.TagDTO;
import com.jeraych.psrm.backend.model.Note;
import com.jeraych.psrm.backend.model.Tag;
import com.jeraych.psrm.backend.repository.NoteRepository;
import com.jeraych.psrm.backend.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TagService {
  private final TagRepository tagRepository;
  private final NoteRepository noteRepository;

  public TagService(TagRepository tagRepository, NoteRepository noteRepository) {this.tagRepository = tagRepository;
    this.noteRepository = noteRepository;
  }

  public List<TagDTO> findAllTags() {
    tagRepository.findAll();
    List<TagDTO> tagDtos = new ArrayList<>();
    for (Tag tag : tagRepository.findAll()) {
      tagDtos.add(Tag.toDTO(tag));
    }
    return tagDtos;
  }

  public TagDTO saveTag(TagDTO tag) {
    Tag tagEntity = Tag.toEntity(tag);
    for (Long noteId : tag.getNoteIds()) {
      Optional<Note> noteEntity = noteRepository.findById(noteId);
      noteEntity.ifPresent(tagEntity::addNote);
    }
    tagRepository.save(tagEntity);
    return Tag.toDTO(tagEntity);
  }

  public TagDTO updateTagById(long id, TagDTO tag) {
    Tag update = tagRepository.findById(id);
    update.setName(tag.getTag_name());
    for (Long noteId : tag.getNoteIds()) {
      Optional<Note> noteEntity = noteRepository.findById(noteId);
      noteEntity.ifPresent(update::addNote);
    }
    tagRepository.save(update);
    return Tag.toDTO(update);
  }

  public void deleteTagById(long id) {tagRepository.deleteById(id);}

  public List<TagDTO> getTagsByIds(List<Long> id) {
    return tagRepository.findAllById(id).stream()
            .map(Tag::toDTO)
            .collect(Collectors.toList());
  }
}
