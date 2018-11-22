package org.artorg.tools.phantomData.server.specification;

import java.util.List;
import java.util.UUID;

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

public abstract class ControllerSpecTemp<T extends Identifiable<ID>, ID extends Comparable<ID>, I_SERVICE_TYPE extends IServiceDefault<T>> {

	@Autowired
	protected I_SERVICE_TYPE service;

	@GetMapping("get-by-id/{id}")
	public ResponseEntity<T> getById(@PathVariable("id") UUID id) {
		T m = service.getById(id);
		return new ResponseEntity<T>(m, HttpStatus.OK);
	}

	@GetMapping("get-all")
	public ResponseEntity<List<T>> getAll() {
		List<T> list = service.getAll();
		return new ResponseEntity<List<T>>(list, HttpStatus.OK);
	}

	@PostMapping("create")
	public ResponseEntity<Void> create(@RequestBody T model, UriComponentsBuilder builder) {
		boolean flag = service.add(model);
		if (flag == false)
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/create/{id}").buildAndExpand(model.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@PutMapping("update")
	public ResponseEntity<T> update(@RequestBody T model) {
		service.update(model);
		return new ResponseEntity<T>(model, HttpStatus.OK);
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
		System.out.println("ControllerSpec: " +id.getClass().getSimpleName());
		service.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("exist-by-id/{id}")
	public ResponseEntity<Boolean> existById(@PathVariable("id") UUID id) {
		Boolean b = service.existById(id);
		return new ResponseEntity<Boolean>(b, HttpStatus.OK);
	}

}
