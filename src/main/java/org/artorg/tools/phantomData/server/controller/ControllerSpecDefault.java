package org.artorg.tools.phantomData.server.controller;

import java.util.UUID;

import org.artorg.tools.phantomData.server.model.Identifiable;
import org.artorg.tools.phantomData.server.service.IServiceDefault;

public abstract class ControllerSpecDefault<T extends Identifiable<UUID>,
	I_SERVICE_TYPE extends IServiceDefault<T>>
	extends ControllerSpec<T, UUID, I_SERVICE_TYPE> {
}
