package org.artorg.tools.phantomData.server.controller.measurement;

import org.artorg.tools.phantomData.server.model.measurement.UnitPrefix;
import org.artorg.tools.phantomData.server.specification.ControllerSpecDefault;
import org.artorg.tools.phantomData.server.specification.IServiceDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("unit-prefixes")
public class UnitPrefixController extends ControllerSpecDefault<UnitPrefix, IServiceDefault<UnitPrefix>> {

}
