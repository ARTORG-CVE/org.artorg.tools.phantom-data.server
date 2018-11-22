package org.artorg.tools.phantomData.server.controller.base.property;

import org.artorg.tools.phantomData.server.model.base.property.PropertyField;
import org.artorg.tools.phantomData.server.model.base.property.StringProperty;
import org.artorg.tools.phantomData.server.serviceSpec.base.property.IstringPropertyService;
import org.artorg.tools.phantomData.server.specification.ControllerSpecDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("string-properties")
public class StringPropertyController extends ControllerSpecDefault<StringProperty, IstringPropertyService<StringProperty>> {

	@GetMapping("get-by-property-field/{property-field}")
	public ResponseEntity<StringProperty> getByPropertyField(
			@PathVariable("property-field") PropertyField propertyField) {
		StringProperty m = service.getByPropertyField(propertyField);
		return new ResponseEntity<StringProperty>(m, HttpStatus.OK);
	}

}
