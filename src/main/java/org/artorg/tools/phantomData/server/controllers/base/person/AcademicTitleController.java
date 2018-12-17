package org.artorg.tools.phantomData.server.controllers.base.person;

import org.artorg.tools.phantomData.server.controller.ControllerSpecDefault;
import org.artorg.tools.phantomData.server.models.base.person.AcademicTitle;
import org.artorg.tools.phantomData.server.service.IServiceDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("academic-titles")
public class AcademicTitleController extends ControllerSpecDefault<AcademicTitle, IServiceDefault<AcademicTitle>> {

}