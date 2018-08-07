package org.artorg.tools.phantomData.server.connector.property;

import org.artorg.tools.phantomData.server.controller.property.BooleanPropertyController;
import org.artorg.tools.phantomData.server.model.property.BooleanProperty;
import org.artorg.tools.phantomData.server.model.property.PropertyField;
import org.artorg.tools.phantomData.server.specification.DatabasePersistent;
import org.artorg.tools.phantomData.server.specification.HttpDatabaseCrud;

public class BooleanPropertyConnector extends HttpDatabaseCrud<BooleanProperty, Integer> {

	private static final BooleanPropertyConnector connector;
	
	static {
		connector = new BooleanPropertyConnector();
	}
	
	public static BooleanPropertyConnector get() {
		return connector;
	}
	
	@Override
	public Class<?> getControllerClass() {
		return BooleanPropertyController.class;
	}

	@Override
	public Class<?> getArrayModelClass() {
		return BooleanProperty[].class;
	}

	@Override
	public Class<? extends DatabasePersistent<BooleanProperty, Integer>> getModelClass() {
		return BooleanProperty.class;
	}

	private final String annoStringReadByPropertyField;
	
	
	private BooleanPropertyConnector() {}
	
	public final String getAnnoStringReadByPropertyField() {
		return annoStringReadByPropertyField;
	}
	
	{
		annoStringReadByPropertyField = super.getAnnotationStringRead("PROPERTY_FIELD");
	}
	
	public BooleanProperty readFirstByPropertyFieldName(PropertyField propertyField) {
		return readByAttribute(propertyField.getId(), getAnnoStringReadByPropertyField());
	}
	
	
}
