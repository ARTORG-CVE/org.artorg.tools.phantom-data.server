package org.artorg.tools.phantomData.server.controllers.base.property;

import org.artorg.tools.phantomData.server.controller.ControllerSpecDefault;
import org.artorg.tools.phantomData.server.models.base.property.DateProperty;
import org.artorg.tools.phantomData.server.models.base.property.PropertyField;
import org.artorg.tools.phantomData.server.serviceSpec.base.property.IdatePropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("date-properties")
public class DatePropertyController extends ControllerSpecDefault<DateProperty, IdatePropertyService<DateProperty>> {

	@GetMapping("get-by-property-field/{property-field}")
	public ResponseEntity<DateProperty> getByPropertyField(
			@PathVariable("property-field") PropertyField propertyField) {
		DateProperty m = service.getByPropertyField(propertyField);
		return new ResponseEntity<DateProperty>(m, HttpStatus.OK);
	}

}
