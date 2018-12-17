package org.artorg.tools.phantomData.server.serviceSpec.base.property;

import org.artorg.tools.phantomData.server.model.Identifiable;
import org.artorg.tools.phantomData.server.models.base.property.DateProperty;
import org.artorg.tools.phantomData.server.models.base.property.PropertyField;
import org.artorg.tools.phantomData.server.service.IServiceDefault;

public interface IdatePropertyService<T extends Identifiable<?>> extends IServiceDefault<T> {
	
	DateProperty getByPropertyField(PropertyField propertyField);

}
