package org.artorg.tools.phantomData.server.serviceSpec.base.property;

import org.artorg.tools.phantomData.server.model.base.property.DoubleProperty;
import org.artorg.tools.phantomData.server.model.base.property.PropertyField;
import org.artorg.tools.phantomData.server.specification.DbPersistent;
import org.artorg.tools.phantomData.server.specification.IService;

public interface IdoublePropertyService <T extends DbPersistent<T,?>> extends IService<T> {
	
	DoubleProperty getByPropertyField(PropertyField propertyField);

}