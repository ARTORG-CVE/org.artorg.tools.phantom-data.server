package org.artorg.tools.phantomData.server.controller.measurement;

import org.artorg.tools.phantomData.server.model.measurement.UnitPrefix;
import org.artorg.tools.phantomData.server.specification.ControllerSpec;
import org.artorg.tools.phantomData.server.specification.IService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("unit-prefixes")
public class UnitPrefixController extends ControllerSpec<UnitPrefix, IService<UnitPrefix>> {

}
