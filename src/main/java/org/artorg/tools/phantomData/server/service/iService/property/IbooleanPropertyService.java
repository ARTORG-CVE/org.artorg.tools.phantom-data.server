package org.artorg.tools.phantomData.server.service.iService.property;

import org.artorg.tools.phantomData.server.model.property.BooleanProperty;
import org.artorg.tools.phantomData.server.model.property.PropertyField;
import org.artorg.tools.phantomData.server.specification.DbPersistentUUID;
import org.artorg.tools.phantomData.server.specification.IService;

public interface IbooleanPropertyService<T extends DbPersistentUUID> extends IService<T> {
	
	BooleanProperty getByPropertyField(PropertyField propertyField);

}