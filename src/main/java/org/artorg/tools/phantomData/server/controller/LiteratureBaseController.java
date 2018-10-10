package org.artorg.tools.phantomData.server.controller;

import java.util.List;
import java.util.UUID;

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
public class LiteratureBaseController extends ControllerSpec<LiteratureBase, IliteratureBaseService<LiteratureBase>> {
	
	@GetMapping("LITERATURE_BASE/BY_SHORTCUT/{SHORTCUT}")
	public ResponseEntity<LiteratureBase> getByShortcut(@PathVariable("SHORTCUT") String shortcut) {
		LiteratureBase m = service.getByShortcut(shortcut);
		return new ResponseEntity<LiteratureBase>(m, HttpStatus.OK);
	}
	
	@Override
	@GetMapping("LITERATURE_BASE/{ID}")
	public ResponseEntity<LiteratureBase> getById(@PathVariable("ID") UUID id) {
		return super.getByIdHelper(id);
	}
	
	@Override
	@GetMapping("LITERATURE_BASES")
	public ResponseEntity<List<LiteratureBase>> getAll() {
		return super.getAllHelper();
	}
	
	@Override
	@PostMapping("LITERATURE_BASE")
	public ResponseEntity<Void> create(@RequestBody LiteratureBase literatureBase, UriComponentsBuilder builder) {
		return super.createHelper(literatureBase, builder);
	}
	
	@Override
	@PutMapping("LITERATURE_BASE")
	public ResponseEntity<LiteratureBase> update(@RequestBody LiteratureBase literatureBase) {
		return super.updateHelper(literatureBase);
	}
	
	@Override
	@DeleteMapping("LITERATURE_BASE/{ID}")
	public ResponseEntity<Void> delete(@PathVariable("ID") UUID id) {
		return super.deleteHelper(id);
	}
	
	@Override
	@GetMapping("LITERATURE_BASE/EXIST_BY_ID/{ID}")
	public ResponseEntity<Boolean> existById(@PathVariable("ID") UUID id) {
		return super.existByIdHelper(id);
	}
	
	@Override
	protected String getModelAnnoString() {
		return "LITERATURE_BASE";
	}

}
