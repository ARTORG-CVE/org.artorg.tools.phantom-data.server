package org.artorg.tools.phantomData.server.controllers.base;

import org.artorg.tools.phantomData.server.controller.ControllerSpecDefault;
import org.artorg.tools.phantomData.server.models.base.Note;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("notes")
public class NoteController extends ControllerSpecDefault<Note> {

}