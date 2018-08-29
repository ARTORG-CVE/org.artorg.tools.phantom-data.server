package org.artorg.tools.phantomData.server.service.iService.property;

import org.artorg.tools.phantomData.server.model.property.DoubleProperty;
import org.artorg.tools.phantomData.server.model.property.PropertyField;
import org.artorg.tools.phantomData.server.specification.DatabasePersistent;
import org.artorg.tools.phantomData.server.specification.IService;

public interface IdoublePropertyService <T extends DatabasePersistent<ID_TYPE>, ID_TYPE> extends IService<T, ID_TYPE> {
	
	DoubleProperty getByPropertyField(PropertyField propertyField);

}