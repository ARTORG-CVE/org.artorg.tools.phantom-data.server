package org.artorg.tools.phantomData.server.service.iService;

import org.artorg.tools.phantomData.server.model.FabricationType;
import org.artorg.tools.phantomData.server.specification.DbPersistent;
import org.artorg.tools.phantomData.server.specification.IService;

public interface IfabricationTypeService<T extends DbPersistent> extends IService<T> {
	
	FabricationType getByShortcut(String shortcut);

}
