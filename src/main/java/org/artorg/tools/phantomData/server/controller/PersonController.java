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
	
	@GetMapping("PERSON/{ID}")
	public ResponseEntity<Person> getById(@PathVariable("ID") UUID id) {
		return super.getById(id);
	}
	
	@GetMapping("PERSONS")
	public ResponseEntity<List<Person>> getAll() {
		return super.getAll();
	}
	
	@PostMapping("PERSON")
	public ResponseEntity<Void> create(@RequestBody Person person, UriComponentsBuilder builder) {
		return super.create(person, builder);
	}
	
	@PutMapping("PERSON")
	public ResponseEntity<Person> update(@RequestBody Person person) {
		return super.update(person);
	}
	
	@DeleteMapping("PERSON/{ID}")
	public ResponseEntity<Void> delete(@PathVariable("ID") UUID id) {
		return super.delete(id);
	}
	
	@Override
	protected String getModelAnnoString() {
		return "PERSON";
	}

}
