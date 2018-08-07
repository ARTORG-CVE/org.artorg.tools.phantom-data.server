package org.artorg.tools.phantomData.server.controller;

import java.util.List;

import org.artorg.tools.phantomData.server.model.FileType;
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
public class FileTypeController extends ControllerSpec<FileType, Integer, 
	IService<FileType,Integer>> {

	@GetMapping("FILE_TYPE/{ID}")
	public ResponseEntity<FileType> getById(@PathVariable("ID") Integer id) {
		return super.getById(id);
	}
	
	@GetMapping("FILE_TYPES")
	public ResponseEntity<List<FileType>> getAll() {
		return super.getAll();
	}
	
	@PostMapping("FILE_TYPE")
	public ResponseEntity<Void> create(@RequestBody FileType fileType, UriComponentsBuilder builder) {
		return super.create(fileType, builder);
	}
	
	@PutMapping("FILE_TYPE")
	public ResponseEntity<FileType> update(@RequestBody FileType fileType) {
		return super.update(fileType);
	}
	
	@DeleteMapping("FILE_TYPE/{ID}")
	public ResponseEntity<Void> delete(@PathVariable("ID") Integer id) {
		return super.delete(id);
	}
	
	@Override
	protected String getModelAnnoString() {
		return "FILE_TYPE";
	}

}
