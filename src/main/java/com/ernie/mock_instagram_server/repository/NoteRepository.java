package com.ernie.mock_instagram_server.repository;

import com.ernie.mock_instagram_server.entity.Note;
import org.springframework.data.repository.CrudRepository;

public interface NoteRepository extends CrudRepository<Note, Integer> {
}
