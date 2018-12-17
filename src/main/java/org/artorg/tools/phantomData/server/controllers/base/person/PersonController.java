package org.artorg.tools.phantomData.server.controllers.base.person;

import org.artorg.tools.phantomData.server.controller.ControllerSpecDefault;
import org.artorg.tools.phantomData.server.models.base.person.Person;
import org.artorg.tools.phantomData.server.service.IServiceDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("persons")
public class PersonController extends ControllerSpecDefault<Person, IServiceDefault<Person>> {

}
