package com.jeraych.psrm.backend.controller;

import com.jeraych.psrm.backend.DTO.TagDTO;
import com.jeraych.psrm.backend.model.Tag;
import com.jeraych.psrm.backend.service.TagService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagController {
  private final TagService tagService;

  public TagController(TagService tagService) {this.tagService = tagService;}

  @PostMapping
  public TagDTO createTag(@RequestBody Tag tag) {return tagService.saveTag(tag);}

  @PutMapping("/{id}")
  public TagDTO updateTag(@RequestBody Tag tag, @PathVariable long id) {return tagService.updateTagById(id, tag);}

  @DeleteMapping("/{id}")
  public void deleteTag(@PathVariable long id) {tagService.deleteTagById(id);}

  @GetMapping
  public List<TagDTO> findAllTagsByIds(@RequestParam(required = false) List<Long> id) {
    if (id == null) {
      return tagService.findAllTags();
    }
    return tagService.getTagsByIds(id);
  }
}
