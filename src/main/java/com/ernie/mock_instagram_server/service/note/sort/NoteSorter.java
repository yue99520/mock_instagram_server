package com.ernie.mock_instagram_server.service.note.sort;

import com.ernie.mock_instagram_server.entity.Note;

import java.util.List;

public interface NoteSorter {

    List<Note> sort(List<Note> notes, SortRule sortBy);

    List<Note> sort(Iterable<Note> notes, SortRule sortBy);
}
