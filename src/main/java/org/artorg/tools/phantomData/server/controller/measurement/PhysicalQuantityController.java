package org.artorg.tools.phantomData.server.controller.measurement;

import org.artorg.tools.phantomData.server.model.measurement.PhysicalQuantity;
import org.artorg.tools.phantomData.server.specification.ControllerSpecDefault;
import org.artorg.tools.phantomData.server.specification.IServiceDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("physical-quantities")
public class PhysicalQuantityController extends ControllerSpecDefault<PhysicalQuantity, IServiceDefault<PhysicalQuantity>> {

}
