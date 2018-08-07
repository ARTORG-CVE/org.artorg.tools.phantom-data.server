package org.artorg.tools.phantomData.server.controller;

import java.util.List;

import org.artorg.tools.phantomData.server.model.LiteratureBase;
import org.artorg.tools.phantomData.server.service.iService.IliteratureBaseService;
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
public class LiteratureBaseController extends ControllerSpec<LiteratureBase, Integer,
		IliteratureBaseService<LiteratureBase,Integer>> {
	
	@GetMapping("LITERATURE_BASE/BY_SHORTCUT/{SHORTCUT}")
	public ResponseEntity<LiteratureBase> getByShortcut(@PathVariable("SHORTCUT") String shortcut) {
		System.out.println("READ BY SHORTCUT");
		LiteratureBase m = service.getByShortcut(shortcut);
		return new ResponseEntity<LiteratureBase>(m, HttpStatus.OK);
	}
	
	@GetMapping("LITERATURE_BASE/{ID}")
	public ResponseEntity<LiteratureBase> getById(@PathVariable("ID") Integer id) {
		return super.getById(id);
	}
	
	@GetMapping("LITERATURE_BASES")
	public ResponseEntity<List<LiteratureBase>> getAll() {
		return super.getAll();
	}
	
	@PostMapping("LITERATURE_BASE")
	public ResponseEntity<Void> create(@RequestBody LiteratureBase literatureBase, UriComponentsBuilder builder) {
		return super.create(literatureBase, builder);
	}
	
	@PutMapping("LITERATURE_BASE")
	public ResponseEntity<LiteratureBase> update(@RequestBody LiteratureBase literatureBase) {
		return super.update(literatureBase);
	}
	
	@DeleteMapping("LITERATURE_BASE/{ID}")
	public ResponseEntity<Void> delete(@PathVariable("ID") Integer id) {
		return super.delete(id);
	}
	
	@Override
	protected String getModelAnnoString() {
		return "LITERATURE_BASE";
	}

}
