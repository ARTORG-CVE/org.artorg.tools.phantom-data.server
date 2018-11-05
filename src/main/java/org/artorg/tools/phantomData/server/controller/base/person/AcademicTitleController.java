package org.artorg.tools.phantomData.server.controller.base.person;

import org.artorg.tools.phantomData.server.model.base.person.AcademicTitle;
import org.artorg.tools.phantomData.server.specification.ControllerSpec;
import org.artorg.tools.phantomData.server.specification.IService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("academic-titles")
public class AcademicTitleController extends ControllerSpec<AcademicTitle, IService<AcademicTitle>> {

}