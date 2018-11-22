package org.artorg.tools.phantomData.server.controller.base.person;

import org.artorg.tools.phantomData.server.model.base.person.Gender;
import org.artorg.tools.phantomData.server.specification.ControllerSpecDefault;
import org.artorg.tools.phantomData.server.specification.IServiceDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("genders")
public class GenderController extends ControllerSpecDefault<Gender, IServiceDefault<Gender>> {	

}