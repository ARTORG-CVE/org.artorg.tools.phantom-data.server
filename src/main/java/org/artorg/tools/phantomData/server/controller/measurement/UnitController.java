package org.artorg.tools.phantomData.server.controller.measurement;

import org.artorg.tools.phantomData.server.model.measurement.Unit;
import org.artorg.tools.phantomData.server.specification.ControllerSpecDefault;
import org.artorg.tools.phantomData.server.specification.IServiceDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("units")
public class UnitController extends ControllerSpecDefault<Unit, IServiceDefault<Unit>> {

}
