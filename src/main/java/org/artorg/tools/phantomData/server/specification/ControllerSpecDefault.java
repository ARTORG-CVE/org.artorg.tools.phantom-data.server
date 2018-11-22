package org.artorg.tools.phantomData.server.specification;

import java.util.UUID;

public abstract class ControllerSpecDefault<T extends Identifiable<UUID>,
	I_SERVICE_TYPE extends IServiceDefault<T>>
	extends ControllerSpecTemp<T, UUID, I_SERVICE_TYPE> {

}
