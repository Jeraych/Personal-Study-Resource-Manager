package com.jeraych.psrm.backend.service;

import com.jeraych.psrm.backend.DTO.TagDTO;
import com.jeraych.psrm.backend.model.Tag;
import com.jeraych.psrm.backend.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagService {
  private final TagRepository tagRepository;

  public TagService(TagRepository tagRepository) {this.tagRepository = tagRepository;}

  public List<TagDTO> findAllTags() {
    tagRepository.findAll();
    List<TagDTO> tagDtos = new ArrayList<>();
    for (Tag tag : tagRepository.findAll()) {
      tagDtos.add(tag.toDTO(tag));
    }
    return tagDtos;
  }

  public TagDTO saveTag(Tag tag) {
    tagRepository.save(tag);
    return tag.toDTO(tag);
  }

  public TagDTO updateTagById(long id, Tag tag) {
    Tag update = tagRepository.findById(id);
    update.setName(tag.getName());
    tagRepository.save(update);
    return tag.toDTO(update);
  }

  public void deleteTagById(long id) {tagRepository.deleteById(id);}
}
