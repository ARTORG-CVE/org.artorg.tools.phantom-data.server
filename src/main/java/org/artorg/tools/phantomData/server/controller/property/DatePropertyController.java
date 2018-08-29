package org.artorg.tools.phantomData.server.controller.property;

import java.util.List;

import org.artorg.tools.phantomData.server.model.property.DateProperty;
import org.artorg.tools.phantomData.server.model.property.PropertyField;
import org.artorg.tools.phantomData.server.service.iService.property.IdatePropertyService;
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
public class DatePropertyController extends 
ControllerSpec<DateProperty, Integer, IdatePropertyService<DateProperty,Integer>> {

	@GetMapping("DATE_PROPERTY/BY_PROPERTY_FIELD/{PROPERTY_FIELD}")
	public ResponseEntity<DateProperty> getByPropertyField(@PathVariable("PROPERTY_FIELD") PropertyField propertyField) {
		DateProperty m = service.getByPropertyField(propertyField);
		return new ResponseEntity<DateProperty>(m, HttpStatus.OK);
	}
	
	@GetMapping("DATE_PROPERTY/{ID}")
	public ResponseEntity<DateProperty> getById(@PathVariable("ID") Integer id) {
		return super.getById(id);
	}
	
	@GetMapping("DATE_PROPERTIES")
	public ResponseEntity<List<DateProperty>> getAll() {
		return super.getAll();
	}
	
	@PostMapping("DATE_PROPERTY")
	public ResponseEntity<Void> create(@RequestBody DateProperty property, UriComponentsBuilder builder) {
		return super.create(property, builder);
	}
	
	@PutMapping("DATE_PROPERTY")
	public ResponseEntity<DateProperty> update(@RequestBody DateProperty property) {
		return super.update(property);
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
