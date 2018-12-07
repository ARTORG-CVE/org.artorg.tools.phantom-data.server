package org.artorg.tools.phantomData.server.controller.phantom;

import org.artorg.tools.phantomData.server.model.phantom.Manufacturing;
import org.artorg.tools.phantomData.server.specification.ControllerSpecDefault;
import org.artorg.tools.phantomData.server.specification.IServiceDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("manufacturings")
public class ManufacturingController extends ControllerSpecDefault<Manufacturing, IServiceDefault<Manufacturing>> {

}
