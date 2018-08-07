package org.artorg.tools.phantomData.server.connector.property;

import org.artorg.tools.phantomData.server.controller.property.DatePropertyController;
import org.artorg.tools.phantomData.server.model.property.DateProperty;
import org.artorg.tools.phantomData.server.specification.DatabasePersistent;
import org.artorg.tools.phantomData.server.specification.HttpDatabaseCrud;

public class DatePropertyConnector extends HttpDatabaseCrud<DateProperty, Integer> {

	private static final DatePropertyConnector connector;
	
	static {
		connector = new DatePropertyConnector();
	}
	
	public static DatePropertyConnector get() {
		return connector;
	}
	
	private DatePropertyConnector() {}
	
	@Override
	public Class<?> getControllerClass() {
		return DatePropertyController.class;
	}

	@Override
	public Class<?> getArrayModelClass() {
		return DateProperty[].class;
	}

	@Override
	public Class<? extends DatabasePersistent<DateProperty, Integer>> getModelClass() {
		return DateProperty.class;
	}

}
