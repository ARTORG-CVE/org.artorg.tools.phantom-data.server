package org.artorg.tools.phantomData.server.controller.measurement;

import org.artorg.tools.phantomData.server.model.measurement.MeasuredValue;
import org.artorg.tools.phantomData.server.specification.ControllerSpecDefault;
import org.artorg.tools.phantomData.server.specification.IServiceDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("measured-values")
public class MeasuredValueController extends ControllerSpecDefault<MeasuredValue, IServiceDefault<MeasuredValue>> {

}
