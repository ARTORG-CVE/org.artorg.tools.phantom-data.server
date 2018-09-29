package org.artorg.tools.phantomData.server.specification;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

public abstract class ControllerSpec<T extends DbPersistent<T,?>, I_SERVICE_TYPE extends IService<T>> {
	
	protected abstract String getModelAnnoString();
	
	@Autowired
	protected I_SERVICE_TYPE service;
	
	protected ResponseEntity<T> getById(@PathVariable("ID") UUID id) {
		T m = service.getById(id);
		return new ResponseEntity<T>(m, HttpStatus.OK);
	}
	
	protected ResponseEntity<List<T>> getAll() {
		List<T> list = service.getAll();
		return new ResponseEntity<List<T>>(list, HttpStatus.OK);
	}
	
	protected ResponseEntity<Void> create(@RequestBody T model, UriComponentsBuilder builder) {
        boolean flag = service.add(model);
        if (flag == false) {
	   return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/" +getModelAnnoString() +"/{ID}").buildAndExpand(model.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	protected ResponseEntity<T> update(@RequestBody T model) {
		service.update(model);
		return new ResponseEntity<T>(model, HttpStatus.OK);
	}
	
	protected ResponseEntity<Void> delete(@PathVariable("ID") UUID id) {
		service.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
