package org.artorg.tools.phantomData.server.controller.property;

import java.util.List;

import org.artorg.tools.phantomData.server.model.property.DoubleProperty;
import org.artorg.tools.phantomData.server.model.property.PropertyField;
import org.artorg.tools.phantomData.server.service.iService.property.IdoublePropertyService;
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
public class DoublePropertyController extends ControllerSpec<DoubleProperty, Integer, 
		IdoublePropertyService<DoubleProperty,Integer>> {

	@GetMapping("DOUBLE_PROPERTY/BY_PROPERTY_FIELD/{PROPERTY_FIELD}")
	public ResponseEntity<DoubleProperty> getByPropertyField(@PathVariable("PROPERTY_FIELD") PropertyField propertyField) {
		DoubleProperty m = service.getByPropertyField(propertyField);
	return new ResponseEntity<DoubleProperty>(m, HttpStatus.OK);
	}
	
	@GetMapping("DOUBLE_PROPERTY/{ID}")
	public ResponseEntity<DoubleProperty> getById(@PathVariable("ID") Integer id) {
	return super.getById(id);
	}
	
	@GetMapping("DOUBLE_PROPERTIES")
	public ResponseEntity<List<DoubleProperty>> getAll() {
	return super.getAll();
	}
	
	@PostMapping("DOUBLE_PROPERTY")
	public ResponseEntity<Void> create(@RequestBody DoubleProperty property, UriComponentsBuilder builder) {
	return super.create(property, builder);
	}
	
	@PutMapping("DOUBLE_PROPERTY")
	public ResponseEntity<DoubleProperty> update(@RequestBody DoubleProperty property) {
	return super.update(property);
	}
	
	@DeleteMapping("DOUBLE_PROPERTY/{ID}")
	public ResponseEntity<Void> delete(@PathVariable("ID") Integer id) {
	return super.delete(id);
	}
	
	@Override
	protected String getModelAnnoString() {
	return "DOUBLE_PROPERTY";
	}

}

