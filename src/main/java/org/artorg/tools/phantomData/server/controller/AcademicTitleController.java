package org.artorg.tools.phantomData.server.controller;

import java.util.List;
import java.util.UUID;

import org.artorg.tools.phantomData.server.model.AcademicTitle;
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
public class AcademicTitleController extends ControllerSpec<AcademicTitle, IService<AcademicTitle>> {
	
	@Override
	@GetMapping("ACADEMIC_TITLE/{ID}")
	public ResponseEntity<AcademicTitle> getById(@PathVariable("ID") UUID id) {
		return super.getByIdHelper(id);
	}
	
	@Override
	@GetMapping("ACADEMIC_TITLES")
	public ResponseEntity<List<AcademicTitle>> getAll() {
		return super.getAllHelper();
	}
	
	@Override
	@PostMapping("ACADEMIC_TITLE")
	public ResponseEntity<Void> create(@RequestBody AcademicTitle academicTitle, UriComponentsBuilder builder) {
		return super.createHelper(academicTitle, builder);
	}
	
	@Override
	@PutMapping("ACADEMIC_TITLE")
	public ResponseEntity<AcademicTitle> update(@RequestBody AcademicTitle academicTitle) {
		return super.updateHelper(academicTitle);
	}
	
	@Override
	@DeleteMapping("ACADEMIC_TITLE/{ID}")
	public ResponseEntity<Void> delete(@PathVariable("ID") UUID id) {
		return super.deleteHelper(id);
	}
	
	@Override
	@GetMapping("ACADEMIC_TITLE/EXIST_BY_ID/{ID}")
	public ResponseEntity<Boolean> existById(@PathVariable("ID") UUID id) {
		return super.existByIdHelper(id);
	}
	
	@Override
	protected String getModelAnnoString() {
		return "ACADEMIC_TITLE";
	}

}