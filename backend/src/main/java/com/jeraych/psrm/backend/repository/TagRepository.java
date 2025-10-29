package com.jeraych.psrm.backend.repository;

import com.jeraych.psrm.backend.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
  Optional<Tag> findByNameIgnoreCase(String name);
  Tag findById(long id);
}
