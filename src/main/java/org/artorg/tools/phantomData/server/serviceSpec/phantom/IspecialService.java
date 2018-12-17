package org.artorg.tools.phantomData.server.serviceSpec.phantom;

import org.artorg.tools.phantomData.server.model.DbPersistent;
import org.artorg.tools.phantomData.server.models.phantom.Special;
import org.artorg.tools.phantomData.server.service.IServiceDefault;

public interface IspecialService<T extends DbPersistent<T,?>> extends IServiceDefault<T> {
	
	Special getByShortcut(String shortcut);
	
}
