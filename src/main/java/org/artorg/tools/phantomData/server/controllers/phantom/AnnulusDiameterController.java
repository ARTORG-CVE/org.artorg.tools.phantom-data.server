package org.artorg.tools.phantomData.server.controllers.phantom;

import java.util.UUID;

import org.artorg.tools.phantomData.server.controller.ControllerSpec;
import org.artorg.tools.phantomData.server.models.phantom.AnnulusDiameter;
import org.artorg.tools.phantomData.server.serviceSpec.phantom.IannulusDiameterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("annulus-diameters")
public class AnnulusDiameterController extends ControllerSpec<AnnulusDiameter, UUID, IannulusDiameterService<AnnulusDiameter>> {
	
	@GetMapping("get-by-shortcut/{shortcut}")
	public ResponseEntity<AnnulusDiameter> getByShortcut(@PathVariable("shortcut") Integer shortcut) {
		AnnulusDiameter m = service.getByShortcut(shortcut);
		return new ResponseEntity<AnnulusDiameter>(m, HttpStatus.OK);
	}
	
}
