package org.artorg.tools.phantomData.server.controller;

import java.util.List;

import org.artorg.tools.phantomData.server.model.Identifiable;
import org.artorg.tools.phantomData.server.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Specifies connection between client and server application on server side.
 * Defines how different operations on entities are reachable by URL mapping.
 * 
 * @param <T>
 * @param <ID>
 * @param <I_SERVICE_TYPE>
 */
public abstract class ControllerSpec<T extends Identifiable<ID>,
		ID extends Comparable<ID>, I_SERVICE_TYPE extends IService<T,ID>> {
	
	@Autowired
	protected I_SERVICE_TYPE service;
	
	@GetMapping("get-by-id/{id}")
	public ResponseEntity<T> getById(@PathVariable("id") ID id) {
		T m = service.getById(id);
		return new ResponseEntity<T>(m, HttpStatus.OK);
	}
	
	@GetMapping("get-all")
	public ResponseEntity<List<T>> getAll() {
		List<T> list = service.getAll();
		return new ResponseEntity<List<T>>(list, HttpStatus.OK);
	}
	
	@PostMapping("create")
	public ResponseEntity<Void> create(@RequestBody T model,
			UriComponentsBuilder builder) {
		boolean flag = service.add(model);
		if (flag == false) return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(
				builder.path("/create/{id}").buildAndExpand(model.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@PutMapping("update")
	public ResponseEntity<T> update(@RequestBody T model) {
		service.update(model);
		return new ResponseEntity<T>(model, HttpStatus.OK);
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") ID id) {
		System.out.println("ControllerSpec: " + id.getClass().getSimpleName());
		service.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("exist-by-id/{id}")
	public ResponseEntity<Boolean> existById(@PathVariable("id") ID id) {
		Boolean b = service.existById(id);
		return new ResponseEntity<Boolean>(b, HttpStatus.OK);
	}
	
}
