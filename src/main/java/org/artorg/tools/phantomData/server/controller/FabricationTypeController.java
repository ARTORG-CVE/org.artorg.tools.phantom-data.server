package org.artorg.tools.phantomData.server.controller;

import java.util.List;

import org.artorg.tools.phantomData.server.model.FabricationType;
import org.artorg.tools.phantomData.server.service.iService.IfabricationTypeService;
import org.artorg.tools.phantomData.server.specification.ControllerSpec;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RequestMapping("user")
public class FabricationTypeController extends ControllerSpec<FabricationType, Integer, 
		IfabricationTypeService<FabricationType,Integer>> {

	@GetMapping("FABRICATION_TYPE/BY_SHORTCUT/{SHORTCUT}")
	public ResponseEntity<FabricationType> getByShortcut(@PathVariable("SHORTCUT") String shortcut) {
		FabricationType m = service.getByShortcut(shortcut);
		return new ResponseEntity<FabricationType>(m, HttpStatus.OK);
	}
	
	@GetMapping("FABRICATION_TYPE/{ID}")
	public ResponseEntity<FabricationType> getById(@PathVariable("ID") Integer id) {
		return super.getById(id);
	}
	
	@GetMapping("FABRICATION_TYPES")
	public ResponseEntity<List<FabricationType>> getAll() {
		return super.getAll();
	}
	
	@PostMapping("FABRICATION_TYPE")
	public ResponseEntity<Void> create(@RequestBody FabricationType fabricationType, UriComponentsBuilder builder) {
		return super.create(fabricationType, builder);
	}
	
	@PutMapping("FABRICATION_TYPE")
	public ResponseEntity<FabricationType> update(@RequestBody FabricationType fabricationType) {
		return super.update(fabricationType);
	}
	
	@DeleteMapping("FABRICATION_TYPE/{ID}")
	public ResponseEntity<Void> delete(@PathVariable("ID") Integer id) {
		return super.delete(id);
	}
	
	@Override
	protected String getModelAnnoString() {
		return "FABRICATION_TYPE";
	}

}
