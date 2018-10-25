package org.artorg.tools.phantomData.server.controller;

import org.artorg.tools.phantomData.server.model.Properties;
import org.artorg.tools.phantomData.server.specification.ControllerSpec;
import org.artorg.tools.phantomData.server.specification.IService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("properties")
public class PropertiesController extends ControllerSpec<Properties, IService<Properties>> {

}