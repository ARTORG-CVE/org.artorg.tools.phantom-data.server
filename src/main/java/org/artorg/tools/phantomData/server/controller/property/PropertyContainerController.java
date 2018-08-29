package org.artorg.tools.phantomData.server.controller.property;

import java.util.List;

import org.artorg.tools.phantomData.server.model.property.PropertyContainer;
import org.artorg.tools.phantomData.server.service.iService.property.IpropertyContainerService;
import org.artorg.tools.phantomData.server.specification.ControllerSpec;
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
public class PropertyContainerController extends ControllerSpec<PropertyContainer, Integer, 
	IpropertyContainerService<PropertyContainer,Integer>> {
	
	@GetMapping("PROPERTY_CONTAINER/{ID}")
	public ResponseEntity<PropertyContainer> getById(@PathVariable("ID") Integer id) {
		return super.getById(id);
	}
	
	@GetMapping("PROPERTY_CONTAINERS")
	public ResponseEntity<List<PropertyContainer>> getAll() {
		return super.getAll();
	}
	
	@PostMapping("PROPERTY_CONTAINER")
	public ResponseEntity<Void> create(@RequestBody PropertyContainer propertyContainer, UriComponentsBuilder builder) {
		return super.create(propertyContainer, builder);
	}
	
	@PutMapping("PROPERTY_CONTAINER")
	public ResponseEntity<PropertyContainer> update(@RequestBody PropertyContainer propertyContainer) {
		return super.update(propertyContainer);
	}
	
	@DeleteMapping("PROPERTY_CONTAINER/{ID}")
	public ResponseEntity<Void> delete(@PathVariable("ID") Integer id) {
		return super.delete(id);
	}
	
	@Override
	protected String getModelAnnoString() {
		return "PROPERTY_CONTAINER";
	}


}
