package com.jeraych.psrm.backend.repository;

import com.jeraych.psrm.backend.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note,Long> {
  Note findById(long id);
}
