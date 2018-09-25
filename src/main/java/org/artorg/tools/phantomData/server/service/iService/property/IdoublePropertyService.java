package org.artorg.tools.phantomData.server.service.iService.property;

import org.artorg.tools.phantomData.server.model.property.DoubleProperty;
import org.artorg.tools.phantomData.server.model.property.PropertyField;
import org.artorg.tools.phantomData.server.specification.DbPersistent;
import org.artorg.tools.phantomData.server.specification.IService;

public interface IdoublePropertyService <T extends DbPersistent> extends IService<T> {
	
	DoubleProperty getByPropertyField(PropertyField propertyField);

}