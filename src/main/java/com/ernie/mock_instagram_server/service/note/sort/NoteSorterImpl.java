package com.ernie.mock_instagram_server.service.note.sort;

import com.ernie.mock_instagram_server.entity.Note;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class NoteSorterImpl implements NoteSorter{
    @Override
    public List<Note> sort(List<Note> notes, SortRule sortBy) {
        //todo: [dev] Sort notes logic
        return notes;
    }

    @Override
    public List<Note> sort(Iterable<Note> notes, SortRule sortBy) {
        //todo: [dev] Sort notes logic
        List<Note> noteList = new ArrayList<>();
        notes.iterator().forEachRemaining(noteList::add);
        return noteList;
    }
}
