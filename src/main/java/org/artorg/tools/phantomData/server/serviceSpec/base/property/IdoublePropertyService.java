package org.artorg.tools.phantomData.server.serviceSpec.base.property;

import org.artorg.tools.phantomData.server.model.base.property.DoubleProperty;
import org.artorg.tools.phantomData.server.model.base.property.PropertyField;
import org.artorg.tools.phantomData.server.specification.DbPersistent;
import org.artorg.tools.phantomData.server.specification.IServiceDefault;

public interface IdoublePropertyService <T extends DbPersistent<T,?>> extends IServiceDefault<T> {
	
	DoubleProperty getByPropertyField(PropertyField propertyField);

}