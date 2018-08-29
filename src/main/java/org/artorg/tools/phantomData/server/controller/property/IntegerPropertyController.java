package org.artorg.tools.phantomData.server.controller.property;

import java.util.List;

import org.artorg.tools.phantomData.server.model.property.IntegerProperty;
import org.artorg.tools.phantomData.server.model.property.PropertyField;
import org.artorg.tools.phantomData.server.service.iService.property.IintegerPropertyService;
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
public class IntegerPropertyController extends ControllerSpec<IntegerProperty, Integer, 
		IintegerPropertyService<IntegerProperty,Integer>> {
	
	@GetMapping("INTEGER_PROPERTY/BY_PROPERTY_FIELD/{PROPERTY_FIELD}")
	public ResponseEntity<IntegerProperty> getByPropertyField(@PathVariable("PROPERTY_FIELD") PropertyField propertyField) {
		IntegerProperty m = service.getByPropertyField(propertyField);
		return new ResponseEntity<IntegerProperty>(m, HttpStatus.OK);
	}
	
	@GetMapping("INTEGER_PROPERTY/{ID}")
	public ResponseEntity<IntegerProperty> getById(@PathVariable("ID") Integer id) {
		return super.getById(id);
	}
	
	@GetMapping("INTEGER_PROPERTIES")
	public ResponseEntity<List<IntegerProperty>> getAll() {
		return super.getAll();
	}
	
	@PostMapping("INTEGER_PROPERTY")
	public ResponseEntity<Void> create(@RequestBody IntegerProperty property, UriComponentsBuilder builder) {
		return super.create(property, builder);
	}
	
	@PutMapping("INTEGER_PROPERTY")
	public ResponseEntity<IntegerProperty> update(@RequestBody IntegerProperty property) {
		return super.update(property);
	}
	
	@DeleteMapping("INTEGER_PROPERTY/{ID}")
	public ResponseEntity<Void> delete(@PathVariable("ID") Integer id) {
		return super.delete(id);
	}
	
	@Override
	protected String getModelAnnoString() {
		return "INTEGER_PROPERTY";
	}

}
