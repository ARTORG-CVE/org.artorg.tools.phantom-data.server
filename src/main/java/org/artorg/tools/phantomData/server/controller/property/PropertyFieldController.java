package org.artorg.tools.phantomData.server.controller.property;

import java.util.List;
import java.util.UUID;

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
public class PropertyFieldController extends ControllerSpec<PropertyField, 
		IpropertyFieldService<PropertyField>> {

	@GetMapping("PROPERTY_FIELD/BY_NAME/{NAME}")
	public ResponseEntity<PropertyField> getByPropertyField(@PathVariable("NAME") String name) {
		PropertyField m = service.getByName(name);
		return new ResponseEntity<PropertyField>(m, HttpStatus.OK);
	}
	
	@Override
	@GetMapping("PROPERTY_FIELD/{ID}")
	public ResponseEntity<PropertyField> getById(@PathVariable("ID") UUID id) {
		return super.getByIdHelper(id);
	}
	
	@Override
	@GetMapping("PROPERTY_FIELDS")
	public ResponseEntity<List<PropertyField>> getAll() {
		return super.getAllHelper();
	}
	
	@Override
	@PostMapping("PROPERTY_FIELD")
	public ResponseEntity<Void> create(@RequestBody PropertyField propertyField, UriComponentsBuilder builder) {
		return super.createHelper(propertyField, builder);
	}
	
	@Override
	@PutMapping("PROPERTY_FIELD")
	public ResponseEntity<PropertyField> update(@RequestBody PropertyField propertyField) {
		return super.updateHelper(propertyField);
	}
	
	@Override
	@DeleteMapping("PROPERTY_FIELD/{ID}")
	public ResponseEntity<Void> delete(@PathVariable("ID") UUID id) {
		return super.deleteHelper(id);
	}
	
	@Override
	@GetMapping("PROPERTY_FIELD/EXIST_BY_ID/{ID}")
	public ResponseEntity<Boolean> existById(@PathVariable("ID") UUID id) {
		return super.existByIdHelper(id);
	}
	
	@Override
	protected String getModelAnnoString() {
		return "PROPERTY_FIELD";
	}

}
