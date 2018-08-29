package org.artorg.tools.phantomData.server.service.iService.property;

import org.artorg.tools.phantomData.server.model.property.PropertyField;
import org.artorg.tools.phantomData.server.model.property.StringProperty;
import org.artorg.tools.phantomData.server.specification.DatabasePersistent;
import org.artorg.tools.phantomData.server.specification.IService;

public interface IstringPropertyService<T extends DatabasePersistent<ID_TYPE>, ID_TYPE> extends IService<T, ID_TYPE> {
	
	StringProperty getByPropertyField(PropertyField propertyField);
	
}
