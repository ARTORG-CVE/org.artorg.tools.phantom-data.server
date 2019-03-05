package org.artorg.tools.phantomData.server.controllers.base.property;

import java.util.UUID;

import org.artorg.tools.phantomData.server.controller.ControllerSpec;
import org.artorg.tools.phantomData.server.models.base.property.IntegerProperty;
import org.artorg.tools.phantomData.server.models.base.property.PropertyField;
import org.artorg.tools.phantomData.server.serviceSpec.base.property.IintegerPropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("integer-properties")
public class IntegerPropertyController extends ControllerSpec<IntegerProperty, UUID, IintegerPropertyService<IntegerProperty>> {
	
	@GetMapping("get-by-property-field/{property-field}")
	public ResponseEntity<IntegerProperty> getByPropertyField(
			@PathVariable("property-field") PropertyField propertyField) {
		IntegerProperty m = service.getByPropertyField(propertyField);
		return new ResponseEntity<IntegerProperty>(m, HttpStatus.OK);
	}

}
