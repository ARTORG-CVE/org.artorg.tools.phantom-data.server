package org.artorg.tools.phantomData.server.serviceSpec.base.property;

import org.artorg.tools.phantomData.server.model.Identifiable;
import org.artorg.tools.phantomData.server.models.base.property.BooleanProperty;
import org.artorg.tools.phantomData.server.models.base.property.PropertyField;
import org.artorg.tools.phantomData.server.service.IServiceDefault;

public interface IbooleanPropertyService<T extends Identifiable<?>> extends IServiceDefault<T> {
	
	BooleanProperty getByPropertyField(PropertyField propertyField);

}