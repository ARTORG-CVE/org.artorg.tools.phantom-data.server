package org.artorg.tools.phantomData.server.controller.property;

import java.util.List;

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
public class StringPropertyController extends ControllerSpec<StringProperty, Integer, 
		IstringPropertyService<StringProperty,Integer>> {
	
	@GetMapping("STRING_PROPERTY/BY_PROPERTY_FIELD/{PROPERTY_FIELD}")
	public ResponseEntity<StringProperty> getByPropertyField(@PathVariable("PROPERTY_FIELD") PropertyField propertyField) {
		StringProperty m = service.getByPropertyField(propertyField);
		return new ResponseEntity<StringProperty>(m, HttpStatus.OK);
	}
	
	@GetMapping("STRING_PROPERTY/{ID}")
	public ResponseEntity<StringProperty> getById(@PathVariable("ID") Integer id) {
		return super.getById(id);
	}
	
	@GetMapping("STRING_PROPERTIES")
	public ResponseEntity<List<StringProperty>> getAll() {
		return super.getAll();
	}
	
	@PostMapping("STRING_PROPERTY")
	public ResponseEntity<Void> create(@RequestBody StringProperty property, UriComponentsBuilder builder) {
		return super.create(property, builder);
	}
	
	@PutMapping("STRING_PROPERTY")
	public ResponseEntity<StringProperty> update(@RequestBody StringProperty property) {
		return super.update(property);
	}
	
	@DeleteMapping("STRING_PROPERTY/{ID}")
	public ResponseEntity<Void> delete(@PathVariable("ID") Integer id) {
		return super.delete(id);
	}

	@Override
	protected String getModelAnnoString() {
		return "STRING_PROPERTY";
	}

}
