package org.artorg.tools.phantomData.server.controllers.phantom;

import org.artorg.tools.phantomData.server.controller.ControllerSpecDefault;
import org.artorg.tools.phantomData.server.models.phantom.Phantomina;
import org.artorg.tools.phantomData.server.service.IServiceDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("phantominas")
public class PhantominaController extends ControllerSpecDefault<Phantomina, IServiceDefault<Phantomina>> {

}
