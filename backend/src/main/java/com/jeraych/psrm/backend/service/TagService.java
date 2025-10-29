package com.jeraych.psrm.backend.service;

import com.jeraych.psrm.backend.model.Tag;
import com.jeraych.psrm.backend.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
  private final TagRepository tagRepository;

  public TagService(TagRepository tagRepository) {this.tagRepository = tagRepository;}

  public List<Tag> findAllTags() {return tagRepository.findAll();}

  public Tag saveTag(Tag tag) {return tagRepository.save(tag);}

  public Tag updateTagById(long id, Tag tag) {
    Tag update = tagRepository.findById(id);
    update.setName(tag.getName());
    return tagRepository.save(update);
  }

  public void deleteTagById(long id) {tagRepository.deleteById(id);}
}
