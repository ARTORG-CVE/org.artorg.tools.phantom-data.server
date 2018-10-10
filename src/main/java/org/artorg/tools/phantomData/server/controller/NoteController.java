package org.artorg.tools.phantomData.server.controller;

import java.util.List;
import java.util.UUID;

import org.artorg.tools.phantomData.server.model.Note;
import org.artorg.tools.phantomData.server.specification.ControllerSpec;
import org.artorg.tools.phantomData.server.specification.IService;
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
public class NoteController extends ControllerSpec<Note, IService<Note>> {
	
	@Override
	@GetMapping("NODE/{ID}")
	public ResponseEntity<Note> getById(@PathVariable("ID") UUID id) {
		return super.getByIdHelper(id);
	}
	
	@Override
	@GetMapping("NODES")
	public ResponseEntity<List<Note>> getAll() {
		return super.getAllHelper();
	}
	
	@Override
	@PostMapping("NODE")
	public ResponseEntity<Void> create(@RequestBody Note node, UriComponentsBuilder builder) {
		return super.createHelper(node, builder);
	}
	
	@Override
	@PutMapping("NODE")
	public ResponseEntity<Note> update(@RequestBody Note node) {
		return super.updateHelper(node);
	}
	
	@Override
	@DeleteMapping("NODE/{ID}")
	public ResponseEntity<Void> delete(@PathVariable("ID") UUID id) {
		return super.deleteHelper(id);
	}
	
	@Override
	@GetMapping("NODE/EXIST_BY_ID/{ID}")
	public ResponseEntity<Boolean> existById(@PathVariable("ID") UUID id) {
		return super.existByIdHelper(id);
	}
	
	@Override
	protected String getModelAnnoString() {
		return "NODE";
	}

}