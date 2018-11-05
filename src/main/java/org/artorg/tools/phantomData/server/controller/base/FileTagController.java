package org.artorg.tools.phantomData.server.controller.base;

import org.artorg.tools.phantomData.server.model.base.FileTag;
import org.artorg.tools.phantomData.server.specification.ControllerSpec;
import org.artorg.tools.phantomData.server.specification.IService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("file-tags")
public class FileTagController extends ControllerSpec<FileTag, IService<FileTag>> {

}