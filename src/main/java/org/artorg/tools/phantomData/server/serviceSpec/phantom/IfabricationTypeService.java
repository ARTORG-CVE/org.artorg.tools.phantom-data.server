package org.artorg.tools.phantomData.server.serviceSpec.phantom;

import org.artorg.tools.phantomData.server.model.phantom.FabricationType;
import org.artorg.tools.phantomData.server.specification.DbPersistent;
import org.artorg.tools.phantomData.server.specification.IService;

public interface IfabricationTypeService<T extends DbPersistent<T,?>> extends IService<T> {
	
	FabricationType getByShortcut(String shortcut);

}
