package org.artorg.tools.phantomData.server.serviceSpec.phantom;

import org.artorg.tools.phantomData.server.model.DbPersistent;
import org.artorg.tools.phantomData.server.models.phantom.AnnulusDiameter;
import org.artorg.tools.phantomData.server.service.IServiceDefault;

public interface IannulusDiameterService<T extends DbPersistent<T,?>> extends IServiceDefault<T> {
	
	AnnulusDiameter getByShortcut(Integer shortcut);
}
