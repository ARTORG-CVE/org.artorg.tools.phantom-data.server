package org.artorg.tools.phantomData.server.specification;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

public abstract class ControllerSpec<MODEL_TYPE extends DatabasePersistent<ID_TYPE>, ID_TYPE, I_SERVICE_TYPE extends IService<MODEL_TYPE, ID_TYPE>> {
	
	protected abstract String getModelAnnoString();
	
	@Autowired
	protected I_SERVICE_TYPE service;
	
	protected Boolean existById(@PathVariable("ID") ID_TYPE id) {
		return service.existById(id);
	}
	
	protected ResponseEntity<MODEL_TYPE> getById(@PathVariable("ID") ID_TYPE id) {
		MODEL_TYPE m = service.getById(id);
		return new ResponseEntity<MODEL_TYPE>(m, HttpStatus.OK);
	}
	
	protected ResponseEntity<List<MODEL_TYPE>> getAll() {
		List<MODEL_TYPE> list = service.getAll();
		return new ResponseEntity<List<MODEL_TYPE>>(list, HttpStatus.OK);
	}
	
	protected ResponseEntity<Void> create(@RequestBody MODEL_TYPE model, UriComponentsBuilder builder) {
        boolean flag = service.add(model);
        if (flag == false) {
	   return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/" +getModelAnnoString() +"/{ID}").buildAndExpand(model.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	protected ResponseEntity<MODEL_TYPE> update(@RequestBody MODEL_TYPE model) {
		service.update(model);
		return new ResponseEntity<MODEL_TYPE>(model, HttpStatus.OK);
	}
	
	protected ResponseEntity<Void> delete(@PathVariable("ID") ID_TYPE id) {
		service.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
