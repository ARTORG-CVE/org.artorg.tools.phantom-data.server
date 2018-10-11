package org.artorg.tools.phantomData.server.controller.property;

import org.artorg.tools.phantomData.server.model.property.PropertyField;
import org.artorg.tools.phantomData.server.model.property.StringProperty;
import org.artorg.tools.phantomData.server.service.iService.property.IstringPropertyService;
import org.artorg.tools.phantomData.server.specification.ControllerSpec;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("string-properties")
public class StringPropertyController extends ControllerSpec<StringProperty, IstringPropertyService<StringProperty>> {

	@GetMapping("get-by-property-field/{property-field}")
	public ResponseEntity<StringProperty> getByPropertyField(
			@PathVariable("property-field") PropertyField propertyField) {
		StringProperty m = service.getByPropertyField(propertyField);
		return new ResponseEntity<StringProperty>(m, HttpStatus.OK);
	}

}
