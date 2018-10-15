package org.artorg.tools.phantomData.server.controller;

import org.artorg.tools.phantomData.server.model.Phantom;
import org.artorg.tools.phantomData.server.specification.ControllerSpec;
import org.artorg.tools.phantomData.server.specification.IService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("phantoms")
public class PhantomController extends ControllerSpec<Phantom, IService<Phantom>> {

}
