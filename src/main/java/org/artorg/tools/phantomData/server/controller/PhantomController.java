package org.artorg.tools.phantomData.server.controller;

import java.util.List;

import org.artorg.tools.phantomData.server.model.Phantom;
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
public class PhantomController extends ControllerSpec<Phantom, Integer, IService<Phantom,Integer>> {

	@GetMapping("PHANTOM/{ID}")
	public ResponseEntity<Phantom> getById(@PathVariable("ID") Integer id) {
		return super.getById(id);
	}
	
	@GetMapping("PHANTOMS")
	public ResponseEntity<List<Phantom>> getAll() {
		return super.getAll();
	}
	
	@PostMapping("PHANTOM")
	public ResponseEntity<Void> create(@RequestBody Phantom phantom, UriComponentsBuilder builder) {
		return super.create(phantom, builder);
	}
	
	@PutMapping("PHANTOM")
	public ResponseEntity<Phantom> update(@RequestBody Phantom phantom) {
		return super.update(phantom);
	}
	
	@DeleteMapping("PHANTOM/{ID}")
	public ResponseEntity<Void> delete(@PathVariable("ID") Integer id) {
		return super.delete(id);
	}
	
	@Override
	protected String getModelAnnoString() {
		return "PHANTOM";
	}

}
