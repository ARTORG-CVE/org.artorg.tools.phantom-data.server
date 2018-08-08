package org.artorg.tools.phantomData.server.controller;

import java.util.List;

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
public class SpecialController extends ControllerSpec<Special, Integer, 
		IspecialService<Special,Integer>> {

	@GetMapping("SPECIAL/BY_SHORTCUT/{SHORTCUT}")
	public ResponseEntity<Special> getByShortcut(@PathVariable("SHORTCUT") String shortcut) {
		Special m = service.getByShortcut(shortcut);
		return new ResponseEntity<Special>(m, HttpStatus.OK);
	}
	
	@GetMapping("SPECIAL/{ID}")
	public ResponseEntity<Special> getById(@PathVariable("ID") Integer id) {
		return super.getById(id);
	}
	
	@GetMapping("SPECIALS")
	public ResponseEntity<List<Special>> getAll() {
		return super.getAll();
	}
	
	@PostMapping("SPECIAL")
	public ResponseEntity<Void> create(@RequestBody Special special, UriComponentsBuilder builder) {
		return super.create(special, builder);
	}
	
	@PutMapping("SPECIAL")
	public ResponseEntity<Special> update(@RequestBody Special special) {
		return super.update(special);
	}
	
	@DeleteMapping("SPECIAL/{ID}")
	public ResponseEntity<Void> delete(@PathVariable("ID") Integer id) {
		return super.delete(id);
	}
	
	@Override
	protected String getModelAnnoString() {
		return "SPECIAL";
	}

}
