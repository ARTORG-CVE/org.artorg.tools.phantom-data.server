package org.artorg.tools.phantomData.server.serviceSpec.phantom;

import org.artorg.tools.phantomData.server.model.phantom.AnnulusDiameter;
import org.artorg.tools.phantomData.server.specification.DbPersistent;
import org.artorg.tools.phantomData.server.specification.IServiceDefault;

public interface IannulusDiameterService<T extends DbPersistent<T,?>> extends IServiceDefault<T> {
	
	AnnulusDiameter getByShortcut(Integer shortcut);
}
