package org.artorg.tools.phantomData.server.serviceSpec.phantom;

import org.artorg.tools.phantomData.server.model.DbPersistent;
import org.artorg.tools.phantomData.server.models.phantom.FabricationType;
import org.artorg.tools.phantomData.server.service.IServiceDefault;

public interface IfabricationTypeService<T extends DbPersistent<T,?>> extends IServiceDefault<T> {
	
	FabricationType getByShortcut(String shortcut);

}
