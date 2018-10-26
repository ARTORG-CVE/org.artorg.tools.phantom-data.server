package org.artorg.tools.phantomData.server.controller;

import org.artorg.tools.phantomData.server.model.person.Gender;
import org.artorg.tools.phantomData.server.specification.ControllerSpec;
import org.artorg.tools.phantomData.server.specification.IService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("genders")
public class GenderController extends ControllerSpec<Gender, IService<Gender>> {	

}