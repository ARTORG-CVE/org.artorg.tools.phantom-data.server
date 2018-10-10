package org.artorg.tools.phantomData.server.controller.property;

import java.util.List;
import java.util.UUID;

import org.artorg.tools.phantomData.server.model.property.PropertyField;
import org.artorg.tools.phantomData.server.model.property.StringProperty;
import org.artorg.tools.phantomData.server.service.iService.property.IstringPropertyService;
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
public class StringPropertyController extends ControllerSpec<StringProperty, 
		IstringPropertyService<StringProperty>> {
	
	@GetMapping("STRING_PROPERTY/BY_PROPERTY_FIELD/{PROPERTY_FIELD}")
	public ResponseEntity<StringProperty> getByPropertyField(@PathVariable("PROPERTY_FIELD") PropertyField propertyField) {
		StringProperty m = service.getByPropertyField(propertyField);
		return new ResponseEntity<StringProperty>(m, HttpStatus.OK);
	}
	
	@Override
	@GetMapping("STRING_PROPERTY/{ID}")
	public ResponseEntity<StringProperty> getById(@PathVariable("ID") UUID id) {
		return super.getByIdHelper(id);
	}
	
	@Override
	@GetMapping("STRING_PROPERTIES")
	public ResponseEntity<List<StringProperty>> getAll() {
		return super.getAllHelper();
	}
	
	@Override
	@PostMapping("STRING_PROPERTY")
	public ResponseEntity<Void> create(@RequestBody StringProperty property, UriComponentsBuilder builder) {
		return super.createHelper(property, builder);
	}
	
	@Override
	@PutMapping("STRING_PROPERTY")
	public ResponseEntity<StringProperty> update(@RequestBody StringProperty property) {
		return super.updateHelper(property);
	}
	
	@Override
	@DeleteMapping("STRING_PROPERTY/{ID}")
	public ResponseEntity<Void> delete(@PathVariable("ID") UUID id) {
		return super.deleteHelper(id);
	}
	
	@Override
	@GetMapping("STRING_PROPERTY/EXIST_BY_ID/{ID}")
	public ResponseEntity<Boolean> existById(@PathVariable("ID") UUID id) {
		return super.existByIdHelper(id);
	}

	@Override
	protected String getModelAnnoString() {
		return "STRING_PROPERTY";
	}

}
