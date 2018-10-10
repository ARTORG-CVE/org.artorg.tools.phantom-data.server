package org.artorg.tools.phantomData.server.controller;

import java.util.List;
import java.util.UUID;

import org.artorg.tools.phantomData.server.model.AnnulusDiameter;
import org.artorg.tools.phantomData.server.service.iService.IannulusDiameterService;
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
public class AnnulusDiameterController extends ControllerSpec<AnnulusDiameter, IannulusDiameterService<AnnulusDiameter>> {
	
	@GetMapping("ANNULUS_DIAMETER/BY_SHORTCUT/{SHORTCUT}")
	public ResponseEntity<AnnulusDiameter> getByShortcut(@PathVariable("SHORTCUT") Integer shortcut) {
		AnnulusDiameter m = service.getByShortcut(shortcut);
		return new ResponseEntity<AnnulusDiameter>(m, HttpStatus.OK);
	}
	
	@Override
	@GetMapping("ANNULUS_DIAMETER/{ID}")
	public ResponseEntity<AnnulusDiameter> getById(@PathVariable("ID") UUID id) {
		return super.getByIdHelper(id);
	}
	
	@Override
	@GetMapping("ANNULUS_DIAMETERS")
	public ResponseEntity<List<AnnulusDiameter>> getAll() {
		return super.getAllHelper();
	}
	
	@Override
	@PostMapping("ANNULUS_DIAMETER")
	public ResponseEntity<Void> create(@RequestBody AnnulusDiameter annulusDiameter, UriComponentsBuilder builder) {
		return super.createHelper(annulusDiameter, builder);
	}
	
	@Override
	@PutMapping("ANNULUS_DIAMETER")
	public ResponseEntity<AnnulusDiameter> update(@RequestBody AnnulusDiameter annulusDiameter) {
		return super.updateHelper(annulusDiameter);
	}
	
	@Override
	@DeleteMapping("ANNULUS_DIAMETER/{ID}")
	public ResponseEntity<Void> delete(@PathVariable("ID") UUID id) {
		return super.deleteHelper(id);
	}
	
	@Override
	@GetMapping("ANNULUS_DIAMETER/EXIST_BY_ID/{ID}")
	public ResponseEntity<Boolean> existById(@PathVariable("ID") UUID id) {
		return super.existByIdHelper(id);
	}

	@Override
	protected String getModelAnnoString() {
		return "ANNULUS_DIAMETER";
	}
	
}
