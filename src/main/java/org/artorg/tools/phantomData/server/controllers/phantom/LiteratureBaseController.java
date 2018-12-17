package org.artorg.tools.phantomData.server.controllers.phantom;

import org.artorg.tools.phantomData.server.controller.ControllerSpecDefault;
import org.artorg.tools.phantomData.server.models.phantom.LiteratureBase;
import org.artorg.tools.phantomData.server.serviceSpec.phantom.IliteratureBaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("literature-bases")
public class LiteratureBaseController extends ControllerSpecDefault<LiteratureBase, IliteratureBaseService<LiteratureBase>> {
	
	@GetMapping("get-by-shortcut/{shortcut}")
	public ResponseEntity<LiteratureBase> getByShortcut(@PathVariable("shortcut") String shortcut) {
		LiteratureBase m = service.getByShortcut(shortcut);
		return new ResponseEntity<LiteratureBase>(m, HttpStatus.OK);
	}

}
