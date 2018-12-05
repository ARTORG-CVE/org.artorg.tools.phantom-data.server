package org.artorg.tools.phantomData.server.controller.measurement;

import org.artorg.tools.phantomData.server.model.measurement.ExperimentalSetup;
import org.artorg.tools.phantomData.server.specification.ControllerSpecDefault;
import org.artorg.tools.phantomData.server.specification.IServiceDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("experimental-setups")
public class ExperimentalSetupController extends ControllerSpecDefault<ExperimentalSetup, IServiceDefault<ExperimentalSetup>> {

}
