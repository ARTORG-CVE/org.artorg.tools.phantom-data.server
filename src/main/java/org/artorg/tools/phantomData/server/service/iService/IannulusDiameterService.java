package org.artorg.tools.phantomData.server.service.iService;

import org.artorg.tools.phantomData.server.model.AnnulusDiameter;
import org.artorg.tools.phantomData.server.specification.DbPersistentUUID;
import org.artorg.tools.phantomData.server.specification.IService;

public interface IannulusDiameterService<T extends DbPersistentUUID> extends IService<T> {
	
	AnnulusDiameter getByShortcut(Integer shortcut);
}
