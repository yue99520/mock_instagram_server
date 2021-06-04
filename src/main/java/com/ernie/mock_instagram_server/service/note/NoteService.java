package com.ernie.mock_instagram_server.service.note;

import com.ernie.mock_instagram_server.dto.model.note.NoteDto;
import com.ernie.mock_instagram_server.service.note.sort.SortRule;

import java.util.List;

public interface NoteService {

    List<NoteDto> findAllNotes();

    List<NoteDto> findAllNotes(SortRule sortBy);

    NoteDto findNoteById(int noteId);

    NoteDto createNote(NoteDto noteDto);

    NoteDto updateNote(NoteDto noteDto);

    Boolean deleteNote(int noteId);
}
