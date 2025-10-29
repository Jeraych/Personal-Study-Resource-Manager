package com.jeraych.psrm.backend.repository;

import com.jeraych.psrm.backend.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note,Long> {
  Note findById(long id);
  List<Note> findByTagsId(long id);
}
