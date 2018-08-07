package org.artorg.tools.phantomData.server.controller.property;

import java.util.List;

import org.artorg.tools.phantomData.server.model.property.DateProperty;
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
public class DatePropertyController extends 
ControllerSpec<DateProperty, Integer, IService<DateProperty,Integer>> {

	@GetMapping("DATE_PROPERTY/{ID}")
	public ResponseEntity<DateProperty> getById(@PathVariable("ID") Integer id) {
		return super.getById(id);
	}
	
	@GetMapping("DATE_PROPERTIES")
	public ResponseEntity<List<DateProperty>> getAll() {
		return super.getAll();
	}
	
	@PostMapping("DATE_PROPERTY")
	public ResponseEntity<Void> create(@RequestBody DateProperty BooleanPropertyField, UriComponentsBuilder builder) {
		return super.create(BooleanPropertyField, builder);
	}
	
	@PutMapping("DATE_PROPERTY")
	public ResponseEntity<DateProperty> update(@RequestBody DateProperty BooleanPropertyField) {
		return super.update(BooleanPropertyField);
	}
	
	@DeleteMapping("DATE_PROPERTY/{ID}")
	public ResponseEntity<Void> delete(@PathVariable("ID") Integer id) {
		return super.delete(id);
	}
	
	@Override
	protected String getModelAnnoString() {
		return "DATE_PROPERTY";
	}

}
