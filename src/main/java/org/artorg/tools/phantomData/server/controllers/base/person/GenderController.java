package org.artorg.tools.phantomData.server.controllers.base.person;

import org.artorg.tools.phantomData.server.controller.ControllerSpecDefault;
import org.artorg.tools.phantomData.server.models.base.person.Gender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("genders")
public class GenderController extends ControllerSpecDefault<Gender> {	

}