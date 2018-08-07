package org.artorg.tools.phantomData.server.controller.property;

import java.util.List;

import org.artorg.tools.phantomData.server.model.property.PropertyField;
import org.artorg.tools.phantomData.server.service.iService.property.IpropertyFieldService;
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
public class PropertyFieldController extends ControllerSpec<PropertyField, Integer, 
		IpropertyFieldService<PropertyField,Integer>> {

	@GetMapping("PROPERTY_FIELD/BY_NAME/{NAME}")
	public ResponseEntity<PropertyField> getByPropertyField(@PathVariable("NAME") String name) {
		System.out.println("READ BY NAME");
		PropertyField m = service.getByName(name);
		return new ResponseEntity<PropertyField>(m, HttpStatus.OK);
	}
	
	@GetMapping("PROPERTY_FIELD/{ID}")
	public ResponseEntity<PropertyField> getById(@PathVariable("ID") Integer id) {
		return super.getById(id);
	}
	
	@GetMapping("PROPERTY_FIELDS")
	public ResponseEntity<List<PropertyField>> getAll() {
		return super.getAll();
	}
	
	@PostMapping("PROPERTY_FIELD")
	public ResponseEntity<Void> create(@RequestBody PropertyField propertyField, UriComponentsBuilder builder) {
		return super.create(propertyField, builder);
	}
	
	@PutMapping("PROPERTY_FIELD")
	public ResponseEntity<PropertyField> update(@RequestBody PropertyField propertyField) {
		return super.update(propertyField);
	}
	
	@DeleteMapping("PROPERTY_FIELD/{ID}")
	public ResponseEntity<Void> delete(@PathVariable("ID") Integer id) {
		return super.delete(id);
	}
	
	@Override
	protected String getModelAnnoString() {
		return "PROPERTY_FIELD";
	}

}
