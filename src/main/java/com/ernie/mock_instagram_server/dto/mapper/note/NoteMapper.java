package com.ernie.mock_instagram_server.dto.mapper.note;

import com.ernie.mock_instagram_server.dto.model.note.NoteDto;
import com.ernie.mock_instagram_server.entity.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteMapper {
    public static NoteDto toNoteDto(Note note) {
        NoteDto noteDto = new NoteDto();
        noteDto.setId(note.getId());
        noteDto.setTitle(note.getTitle());
        noteDto.setContent(note.getContent());
        noteDto.setColor(note.getColor());
        return noteDto;
    }

    public static List<NoteDto> toNoteDtoList(List<Note> notes) {
        List<NoteDto> noteDtos = new ArrayList<>();
        for (Note note : notes) {
            noteDtos.add(NoteMapper.toNoteDto(note));
        }
        return noteDtos;
    }
}
