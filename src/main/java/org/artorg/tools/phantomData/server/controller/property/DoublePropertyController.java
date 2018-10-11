package org.artorg.tools.phantomData.server.controller.property;

import org.artorg.tools.phantomData.server.model.property.DoubleProperty;
import org.artorg.tools.phantomData.server.model.property.PropertyField;
import org.artorg.tools.phantomData.server.service.iService.property.IdoublePropertyService;
import org.artorg.tools.phantomData.server.specification.ControllerSpec;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("double-properties")
public class DoublePropertyController extends ControllerSpec<DoubleProperty, IdoublePropertyService<DoubleProperty>> {
	
	@GetMapping("get-by-property-field/{property-field}")
	public ResponseEntity<DoubleProperty> getByPropertyField(
			@PathVariable("property-field") PropertyField propertyField) {
		DoubleProperty m = service.getByPropertyField(propertyField);
		return new ResponseEntity<DoubleProperty>(m, HttpStatus.OK);
	}

}

