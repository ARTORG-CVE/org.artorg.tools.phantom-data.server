package org.artorg.tools.phantomData.server.controller;

import org.artorg.tools.phantomData.server.model.Phantom;
import org.artorg.tools.phantomData.server.specification.ControllerSpec;
import org.artorg.tools.phantomData.server.specification.IService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("phantoms")
public class PhantomController extends ControllerSpec<Phantom, IService<Phantom>> {
	
//	@Override
//	@GetMapping("PHANTOM/{ID}")
//	public ResponseEntity<Phantom> getById(@PathVariable("ID") UUID id) {
//		return super.getByIdHelper(id);
//	}
//	
//	@Override
//	@GetMapping("PHANTOMS")
//	public ResponseEntity<List<Phantom>> getAll() {
//		return super.getAllHelper();
//	}
//	
//	@Override
//	@PostMapping("PHANTOM")
//	public ResponseEntity<Void> create(@RequestBody Phantom phantom, UriComponentsBuilder builder) {
//		return super.createHelper(phantom, builder);
//	}
//	
//	@Override
//	@PutMapping("PHANTOM")
//	public ResponseEntity<Phantom> update(@RequestBody Phantom phantom) {
//		return super.updateHelper(phantom);
//	}
//	
//	@Override
//	@DeleteMapping("PHANTOM/{ID}")
//	public ResponseEntity<Void> delete(@PathVariable("ID") UUID id) {
//		return super.deleteHelper(id);
//	}
//	
//	@Override
//	@GetMapping("PHANTOM/EXIST_BY_ID/{ID}")
//	public ResponseEntity<Boolean> existById(@PathVariable("ID") UUID id) {
//		return super.existByIdHelper(id);
//	}
//	
//	@Override
//	protected String getModelAnnoString() {
//		return "PHANTOM";
//	}

}
