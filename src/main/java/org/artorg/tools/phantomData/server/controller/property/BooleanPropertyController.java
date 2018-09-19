package org.artorg.tools.phantomData.server.controller.property;

import java.util.List;
import java.util.UUID;

import org.artorg.tools.phantomData.server.model.property.BooleanProperty;
import org.artorg.tools.phantomData.server.model.property.PropertyField;
import org.artorg.tools.phantomData.server.service.iService.property.IbooleanPropertyService;
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
public class BooleanPropertyController extends ControllerSpec<BooleanProperty, 
		IbooleanPropertyService<BooleanProperty>> {

	@GetMapping("BOOLEAN_PROPERTY/BY_PROPERTY_FIELD/{PROPERTY_FIELD}")
	public ResponseEntity<BooleanProperty> getByPropertyField(@PathVariable("PROPERTY_FIELD") PropertyField propertyField) {
		BooleanProperty m = service.getByPropertyField(propertyField);
		return new ResponseEntity<BooleanProperty>(m, HttpStatus.OK);
	}
	
	@GetMapping("BOOLEAN_PROPERTY/{ID}")
	public ResponseEntity<BooleanProperty> getById(@PathVariable("ID") UUID id) {
		return super.getById(id);
	}
	
	@GetMapping("BOOLEAN_PROPERTIES")
	public ResponseEntity<List<BooleanProperty>> getAll() {
		return super.getAll();
	}
	
	@PostMapping("BOOLEAN_PROPERTY")
	public ResponseEntity<Void> create(@RequestBody BooleanProperty property, UriComponentsBuilder builder) {
		return super.create(property, builder);
	}
	
	@PutMapping("BOOLEAN_PROPERTY")
	public ResponseEntity<BooleanProperty> update(@RequestBody BooleanProperty property) {
		return super.update(property);
	}
	
	@DeleteMapping("BOOLEAN_PROPERTY/{ID}")
	public ResponseEntity<Void> delete(@PathVariable("ID") UUID id) {
		return super.delete(id);
	}
	
	@Override
	protected String getModelAnnoString() {
		return "BOOLEAN_PROPERTY";
	}

}
