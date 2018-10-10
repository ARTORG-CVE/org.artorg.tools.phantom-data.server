package org.artorg.tools.phantomData.server.controller;

import java.util.List;
import java.util.UUID;

import org.artorg.tools.phantomData.server.model.Person;
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
public class PersonController extends ControllerSpec<Person, IService<Person>> {
	
	@Override
	@GetMapping("PERSON/{ID}")
	public ResponseEntity<Person> getById(@PathVariable("ID") UUID id) {
		return super.getByIdHelper(id);
	}
	
	@Override
	@GetMapping("PERSONS")
	public ResponseEntity<List<Person>> getAll() {
		return super.getAllHelper();
	}
	
	@Override
	@PostMapping("PERSON")
	public ResponseEntity<Void> create(@RequestBody Person person, UriComponentsBuilder builder) {
		return super.createHelper(person, builder);
	}
	
	@Override
	@PutMapping("PERSON")
	public ResponseEntity<Person> update(@RequestBody Person person) {
		return super.updateHelper(person);
	}
	
	@Override
	@DeleteMapping("PERSON/{ID}")
	public ResponseEntity<Void> delete(@PathVariable("ID") UUID id) {
		return super.deleteHelper(id);
	}
	
	@Override
	@GetMapping("PERSON/EXIST_BY_ID/{ID}")
	public ResponseEntity<Boolean> existById(@PathVariable("ID") UUID id) {
		return super.existByIdHelper(id);
	}
	
	@Override
	protected String getModelAnnoString() {
		return "PERSON";
	}

}
