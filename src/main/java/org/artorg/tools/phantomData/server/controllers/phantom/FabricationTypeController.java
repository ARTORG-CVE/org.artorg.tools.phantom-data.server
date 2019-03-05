package org.artorg.tools.phantomData.server.controllers.phantom;

import java.util.UUID;

import org.artorg.tools.phantomData.server.controller.ControllerSpec;
import org.artorg.tools.phantomData.server.models.phantom.FabricationType;
import org.artorg.tools.phantomData.server.serviceSpec.phantom.IfabricationTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("fabrication-types")
public class FabricationTypeController
		extends ControllerSpec<FabricationType, UUID, IfabricationTypeService<FabricationType>> {

	@GetMapping("get-by-shortcut/{shortcut}")
	public ResponseEntity<FabricationType> getByShortcut(@PathVariable("shortcut") String shortcut) {
		FabricationType m = service.getByShortcut(shortcut);
		return new ResponseEntity<FabricationType>(m, HttpStatus.OK);
	}

}
