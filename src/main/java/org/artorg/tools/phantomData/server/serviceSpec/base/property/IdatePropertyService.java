package org.artorg.tools.phantomData.server.serviceSpec.base.property;

import org.artorg.tools.phantomData.server.model.base.property.DateProperty;
import org.artorg.tools.phantomData.server.model.base.property.PropertyField;
import org.artorg.tools.phantomData.server.specification.IServiceDefault;
import org.artorg.tools.phantomData.server.specification.Identifiable;

public interface IdatePropertyService<T extends Identifiable<?>> extends IServiceDefault<T> {
	
	DateProperty getByPropertyField(PropertyField propertyField);

}
