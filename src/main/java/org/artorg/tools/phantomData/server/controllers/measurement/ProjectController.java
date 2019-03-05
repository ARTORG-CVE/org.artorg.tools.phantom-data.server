package org.artorg.tools.phantomData.server.controllers.measurement;

import org.artorg.tools.phantomData.server.controller.ControllerSpecDefault;
import org.artorg.tools.phantomData.server.models.measurement.Project;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("projects")
public class ProjectController extends ControllerSpecDefault<Project> {

}
