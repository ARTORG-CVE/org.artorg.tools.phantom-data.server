package org.artorg.tools.phantomData.server.service.iService.property;

import org.artorg.tools.phantomData.server.model.property.PropertyField;
import org.artorg.tools.phantomData.server.model.property.StringProperty;
import org.artorg.tools.phantomData.server.specification.DbPersistent;
import org.artorg.tools.phantomData.server.specification.IService;

public interface IstringPropertyService<T extends DbPersistent<T,?>> extends IService<T> {
	
	StringProperty getByPropertyField(PropertyField propertyField);
	
}
