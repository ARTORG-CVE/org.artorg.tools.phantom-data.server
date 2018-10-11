package org.artorg.tools.phantomData.server.controller;

import org.artorg.tools.phantomData.server.model.FileType;
import org.artorg.tools.phantomData.server.specification.ControllerSpec;
import org.artorg.tools.phantomData.server.specification.IService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("file-types")
public class FileTypeController extends ControllerSpec<FileType, IService<FileType>> {
	
	

}
