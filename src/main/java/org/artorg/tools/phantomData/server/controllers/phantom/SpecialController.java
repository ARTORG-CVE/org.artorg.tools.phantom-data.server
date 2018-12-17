package org.artorg.tools.phantomData.server.controllers.phantom;

import org.artorg.tools.phantomData.server.controller.ControllerSpecDefault;
import org.artorg.tools.phantomData.server.models.phantom.Special;
import org.artorg.tools.phantomData.server.serviceSpec.phantom.IspecialService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("specials")
public class SpecialController extends ControllerSpecDefault<Special, IspecialService<Special>> {

	@GetMapping("get-by-shortcut/{shortcut}")
	public ResponseEntity<Special> getByShortcut(@PathVariable("shortcut") String shortcut) {
		Special m = service.getByShortcut(shortcut);
		return new ResponseEntity<Special>(m, HttpStatus.OK);
	}

}
