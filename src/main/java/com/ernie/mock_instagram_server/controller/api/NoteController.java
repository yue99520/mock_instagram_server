package com.ernie.mock_instagram_server.controller.api;


import com.ernie.mock_instagram_server.service.note.NoteService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/notes")
public class NoteController {

    private NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }
}
