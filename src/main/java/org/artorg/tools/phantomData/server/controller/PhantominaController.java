package org.artorg.tools.phantomData.server.controller;

import org.artorg.tools.phantomData.server.model.phantom.Phantomina;
import org.artorg.tools.phantomData.server.specification.ControllerSpec;
import org.artorg.tools.phantomData.server.specification.IService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("phantominas")
public class PhantominaController extends ControllerSpec<Phantomina, IService<Phantomina>> {

}
