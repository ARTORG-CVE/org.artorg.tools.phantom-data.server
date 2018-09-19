package org.artorg.tools.phantomData.server.service.iService.property;

import org.artorg.tools.phantomData.server.model.property.BooleanProperty;
import org.artorg.tools.phantomData.server.model.property.PropertyField;
import org.artorg.tools.phantomData.server.specification.DatabasePersistent;
import org.artorg.tools.phantomData.server.specification.IService;

public interface IbooleanPropertyService<T extends DatabasePersistent> extends IService<T> {
	
	BooleanProperty getByPropertyField(PropertyField propertyField);

}