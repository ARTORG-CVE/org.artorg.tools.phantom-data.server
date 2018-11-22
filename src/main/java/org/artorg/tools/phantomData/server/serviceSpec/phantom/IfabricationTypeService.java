package org.artorg.tools.phantomData.server.serviceSpec.phantom;

import org.artorg.tools.phantomData.server.model.phantom.FabricationType;
import org.artorg.tools.phantomData.server.specification.DbPersistent;
import org.artorg.tools.phantomData.server.specification.IServiceDefault;

public interface IfabricationTypeService<T extends DbPersistent<T,?>> extends IServiceDefault<T> {
	
	FabricationType getByShortcut(String shortcut);

}
