package org.artorg.tools.phantomData.server.controller;

import org.artorg.tools.phantomData.server.model.person.Person;
import org.artorg.tools.phantomData.server.specification.ControllerSpec;
import org.artorg.tools.phantomData.server.specification.IService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("persons")
public class PersonController extends ControllerSpec<Person, IService<Person>> {

}
