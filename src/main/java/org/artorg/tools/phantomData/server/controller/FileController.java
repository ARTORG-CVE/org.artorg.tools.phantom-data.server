package org.artorg.tools.phantomData.server.controller;

import java.util.List;

import org.artorg.tools.phantomData.server.model.PhantomFile;
import org.artorg.tools.phantomData.server.service.iService.IfileService;
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
public class FileController extends ControllerSpec<PhantomFile, Integer, 
		IfileService<PhantomFile,Integer>> {

	@GetMapping("FILE/BY_NAME/{NAME}")
	public ResponseEntity<PhantomFile> getByName(@PathVariable("NAME") String name) {
		PhantomFile f = service.getByName(name);
		return new ResponseEntity<PhantomFile>(f, HttpStatus.OK);
	}
	
	@GetMapping("FILE/{ID}")
	public ResponseEntity<PhantomFile> getById(@PathVariable("ID") Integer id) {
		return super.getById(id);
	}
	
	@GetMapping("FILES")
	public ResponseEntity<List<PhantomFile>> getAll() {
		return super.getAll();
	}
	
	@PostMapping("FILE")
	public ResponseEntity<Void> create(@RequestBody PhantomFile file, UriComponentsBuilder builder) {
		return super.create(file, builder);
	}
	
	@PutMapping("FILE")
	public ResponseEntity<PhantomFile> update(@RequestBody PhantomFile file) {
		return super.update(file);
	}
	
	@DeleteMapping("FILE/{ID}")
	public ResponseEntity<Void> delete(@PathVariable("ID") Integer id) {
		return super.delete(id);
	}
	
	@Override
	protected String getModelAnnoString() {
		return "FILE";
	}

}
