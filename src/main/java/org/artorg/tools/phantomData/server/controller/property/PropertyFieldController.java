package org.artorg.tools.phantomData.server.controller.property;

import org.artorg.tools.phantomData.server.model.property.PropertyField;
import org.artorg.tools.phantomData.server.service.iService.property.IpropertyFieldService;
import org.artorg.tools.phantomData.server.specification.ControllerSpec;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("property-fields")
public class PropertyFieldController extends ControllerSpec<PropertyField, 
		IpropertyFieldService<PropertyField>> {
	
	@GetMapping("get-by-name/{name}")
	public ResponseEntity<PropertyField> getByPropertyField(@PathVariable("name") String name) {
		PropertyField m = service.getByName(name);
		return new ResponseEntity<PropertyField>(m, HttpStatus.OK);
	}

}
