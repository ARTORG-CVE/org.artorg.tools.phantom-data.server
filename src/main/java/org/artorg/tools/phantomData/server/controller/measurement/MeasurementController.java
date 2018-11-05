package org.artorg.tools.phantomData.server.controller.measurement;

import org.artorg.tools.phantomData.server.model.measurement.Measurement;
import org.artorg.tools.phantomData.server.specification.ControllerSpec;
import org.artorg.tools.phantomData.server.specification.IService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("measurements")
public class MeasurementController
	extends ControllerSpec<Measurement, IService<Measurement>> {

}
