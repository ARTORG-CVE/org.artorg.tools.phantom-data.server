package org.artorg.tools.phantomData.server.serviceSpec.base.property;

import org.artorg.tools.phantomData.server.model.DbPersistent;
import org.artorg.tools.phantomData.server.models.base.property.DoubleProperty;
import org.artorg.tools.phantomData.server.models.base.property.PropertyField;
import org.artorg.tools.phantomData.server.service.IServiceDefault;

public interface IdoublePropertyService <T extends DbPersistent<T,?>> extends IServiceDefault<T> {
	
	DoubleProperty getByPropertyField(PropertyField propertyField);

}