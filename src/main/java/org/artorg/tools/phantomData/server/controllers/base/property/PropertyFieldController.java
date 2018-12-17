package org.artorg.tools.phantomData.server.controllers.base.property;

import org.artorg.tools.phantomData.server.controller.ControllerSpecDefault;
import org.artorg.tools.phantomData.server.models.base.property.PropertyField;
import org.artorg.tools.phantomData.server.serviceSpec.base.property.IpropertyFieldService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("property-fields")
public class PropertyFieldController extends ControllerSpecDefault<PropertyField, 
		IpropertyFieldService<PropertyField>> {
	
	@GetMapping("get-by-name/{name}")
	public ResponseEntity<PropertyField> getByPropertyField(@PathVariable("name") String name) {
		PropertyField m = service.getByName(name);
		return new ResponseEntity<PropertyField>(m, HttpStatus.OK);
	}

}
