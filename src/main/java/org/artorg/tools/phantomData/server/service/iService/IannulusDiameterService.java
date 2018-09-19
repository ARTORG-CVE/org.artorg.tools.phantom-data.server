package org.artorg.tools.phantomData.server.service.iService;

import org.artorg.tools.phantomData.server.model.AnnulusDiameter;
import org.artorg.tools.phantomData.server.specification.DatabasePersistent;
import org.artorg.tools.phantomData.server.specification.IService;

public interface IannulusDiameterService<T extends DatabasePersistent> extends IService<T> {
	
	AnnulusDiameter getByShortcut(Integer shortcut);
}
