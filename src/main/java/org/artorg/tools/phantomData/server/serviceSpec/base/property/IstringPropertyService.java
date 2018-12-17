package org.artorg.tools.phantomData.server.serviceSpec.base.property;

import org.artorg.tools.phantomData.server.model.DbPersistent;
import org.artorg.tools.phantomData.server.models.base.property.PropertyField;
import org.artorg.tools.phantomData.server.models.base.property.StringProperty;
import org.artorg.tools.phantomData.server.service.IServiceDefault;

public interface IstringPropertyService<T extends DbPersistent<T,?>> extends IServiceDefault<T> {
	
	StringProperty getByPropertyField(PropertyField propertyField);
	
}
