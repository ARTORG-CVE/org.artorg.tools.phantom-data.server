package org.artorg.tools.phantomData.server.controllers.base.property;

import java.util.UUID;

import org.artorg.tools.phantomData.server.controller.ControllerSpec;
import org.artorg.tools.phantomData.server.models.base.property.BooleanProperty;
import org.artorg.tools.phantomData.server.models.base.property.PropertyField;
import org.artorg.tools.phantomData.server.serviceSpec.base.property.IbooleanPropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("boolean-properties")
public class BooleanPropertyController
		extends ControllerSpec<BooleanProperty, UUID, IbooleanPropertyService<BooleanProperty>> {

	@GetMapping("get-by-property-field/{property-field}")
	public ResponseEntity<BooleanProperty> getByPropertyField(
			@PathVariable("property-field") PropertyField propertyField) {
		BooleanProperty m = service.getByPropertyField(propertyField);
		return new ResponseEntity<BooleanProperty>(m, HttpStatus.OK);
	}

}
