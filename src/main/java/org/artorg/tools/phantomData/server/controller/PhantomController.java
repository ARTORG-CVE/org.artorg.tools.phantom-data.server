package org.artorg.tools.phantomData.server.controller;

import java.util.List;
import java.util.UUID;

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
public class PhantomController extends ControllerSpec<Phantom, IService<Phantom>> {
	
	@Override
	@GetMapping("PHANTOM/{ID}")
	public ResponseEntity<Phantom> getById(@PathVariable("ID") UUID id) {
		return super.getByIdHelper(id);
	}
	
	@Override
	@GetMapping("PHANTOMS")
	public ResponseEntity<List<Phantom>> getAll() {
		return super.getAllHelper();
	}
	
	@Override
	@PostMapping("PHANTOM")
	public ResponseEntity<Void> create(@RequestBody Phantom phantom, UriComponentsBuilder builder) {
		return super.createHelper(phantom, builder);
	}
	
	@Override
	@PutMapping("PHANTOM")
	public ResponseEntity<Phantom> update(@RequestBody Phantom phantom) {
		return super.updateHelper(phantom);
	}
	
	@Override
	@DeleteMapping("PHANTOM/{ID}")
	public ResponseEntity<Void> delete(@PathVariable("ID") UUID id) {
		return super.deleteHelper(id);
	}
	
	@Override
	@GetMapping("PHANTOM/EXIST_BY_ID/{ID}")
	public ResponseEntity<Boolean> existById(@PathVariable("ID") UUID id) {
		return super.existByIdHelper(id);
	}
	
	@Override
	protected String getModelAnnoString() {
		return "PHANTOM";
	}

}
