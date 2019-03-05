package org.artorg.tools.phantomData.server.controllers.phantom;

import org.artorg.tools.phantomData.server.controller.ControllerSpecDefault;
import org.artorg.tools.phantomData.server.models.phantom.SimulationPhantom;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("simulation-phantoms")
public class SimulationPhantomController extends ControllerSpecDefault<SimulationPhantom> {

}
