package org.artorg.tools.phantomData.server.controller;

import java.util.UUID;

import org.artorg.tools.phantomData.server.model.Identifiable;
import org.artorg.tools.phantomData.server.service.IServiceDefault;

/**
 * Simplifies implementations of controller classes for this server application.
 * Default means, that it is especially for this project. Default in other
 * project can be different.
 * 
 * @param <T> Entity type
 */
public abstract class ControllerSpecDefault<T extends Identifiable<UUID>>
		extends ControllerSpec<T, UUID, IServiceDefault<T>> {}
