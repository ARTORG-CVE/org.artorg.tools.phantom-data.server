package org.artorg.tools.phantomData.server.controllers.measurement;

import org.artorg.tools.phantomData.server.controller.ControllerSpecDefault;
import org.artorg.tools.phantomData.server.models.measurement.Measurement;
import org.artorg.tools.phantomData.server.service.IServiceDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("measurements")
public class MeasurementController
	extends ControllerSpecDefault<Measurement, IServiceDefault<Measurement>> {

}
