package org.artorg.tools.phantomData.server.controller;

import java.util.List;
import java.util.UUID;

import org.artorg.tools.phantomData.server.model.Special;
import org.artorg.tools.phantomData.server.service.iService.IspecialService;
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
public class SpecialController extends ControllerSpec<Special, IspecialService<Special>> {

	@GetMapping("SPECIAL/BY_SHORTCUT/{SHORTCUT}")
	public ResponseEntity<Special> getByShortcut(@PathVariable("SHORTCUT") String shortcut) {
		Special m = service.getByShortcut(shortcut);
		return new ResponseEntity<Special>(m, HttpStatus.OK);
	}
	
	@Override
	@GetMapping("SPECIAL/{ID}")
	public ResponseEntity<Special> getById(@PathVariable("ID") UUID id) {
		return super.getByIdHelper(id);
	}
	
	@Override
	@GetMapping("SPECIALS")
	public ResponseEntity<List<Special>> getAll() {
		return super.getAllHelper();
	}
	
	@Override
	@PostMapping("SPECIAL")
	public ResponseEntity<Void> create(@RequestBody Special special, UriComponentsBuilder builder) {
		return super.createHelper(special, builder);
	}
	
	@Override
	@PutMapping("SPECIAL")
	public ResponseEntity<Special> update(@RequestBody Special special) {
		return super.updateHelper(special);
	}
	
	@Override
	@DeleteMapping("SPECIAL/{ID}")
	public ResponseEntity<Void> delete(@PathVariable("ID") UUID id) {
		return super.deleteHelper(id);
	}
	
	@Override
	@GetMapping("SPECIAL/EXIST_BY_ID/{ID}")
	public ResponseEntity<Boolean> existById(@PathVariable("ID") UUID id) {
		return super.existByIdHelper(id);
	}
	
	@Override
	protected String getModelAnnoString() {
		return "SPECIAL";
	}

}
