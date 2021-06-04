package com.ernie.mock_instagram_server.service.note;

import com.ernie.mock_instagram_server.dto.mapper.note.NoteMapper;
import com.ernie.mock_instagram_server.dto.model.note.NoteDto;
import com.ernie.mock_instagram_server.entity.Note;
import com.ernie.mock_instagram_server.repository.NoteRepository;
import com.ernie.mock_instagram_server.service.note.sort.NoteSorter;
import com.ernie.mock_instagram_server.service.note.sort.SortRule;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class NoteServiceImpl implements NoteService{

    private final NoteRepository noteRepository;

    private final NoteSorter noteSorter;

    public NoteServiceImpl(NoteRepository noteRepository, NoteSorter noteSorter) {
        this.noteRepository = noteRepository;
        this.noteSorter = noteSorter;
    }

    @Override
    public List<NoteDto> findAllNotes() {
        return this.findAllNotes(SortRule.BY_NOTHING);
    }

    @Override
    public List<NoteDto> findAllNotes(SortRule sortBy) {
        Iterable<Note> notes = this.noteRepository.findAll();
        List<Note> sortedNotes = this.noteSorter.sort(notes, sortBy);
        return NoteMapper.toNoteDtoList(sortedNotes);
    }

    @Override
    public NoteDto findNoteById(int noteId) {
        Optional<Note> optionalNote = this.noteRepository.findById(noteId);
        if (optionalNote.isPresent()) {
            return NoteMapper.toNoteDto(optionalNote.get());
        }
        throw new EntityNotFoundException(String.format("Note not found(id=%d).", noteId));
    }

    @Override
    public NoteDto createNote(NoteDto noteDto) {
        Date now = new Date();
        Note note = new Note();

        note.setTitle(noteDto.getTitle());
        note.setContent(noteDto.getContent());
        note.setColor(noteDto.getColor());

        note.setUpdatedAt(now);
        note.setCreatedAt(now);

        note = this.noteRepository.save(note);
        return NoteMapper.toNoteDto(note);
    }

    @Override
    public NoteDto updateNote(NoteDto noteDto) {
        Date now = new Date();

        Optional<Note> optionalNote = this.noteRepository.findById(noteDto.getId());
        if (optionalNote.isPresent()) {
            Note note = optionalNote.get();
            note.setTitle(noteDto.getTitle());
            note.setContent(noteDto.getContent());
            note.setColor(noteDto.getColor());
            note.setUpdatedAt(now);
            return NoteMapper.toNoteDto(note);
        }
        throw new EntityNotFoundException(String.format("Note not found(id=%d).", noteDto.getId()));
    }

    @Override
    public Boolean deleteNote(int noteId) {
        if (this.noteRepository.existsById(noteId)) {
            this.noteRepository.deleteById(noteId);
        }
        return !this.noteRepository.existsById(noteId);
    }
}
