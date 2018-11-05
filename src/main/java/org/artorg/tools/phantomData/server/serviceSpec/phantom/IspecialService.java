package org.artorg.tools.phantomData.server.serviceSpec.phantom;

import org.artorg.tools.phantomData.server.model.phantom.Special;
import org.artorg.tools.phantomData.server.specification.DbPersistent;
import org.artorg.tools.phantomData.server.specification.IService;

public interface IspecialService<T extends DbPersistent<T,?>> extends IService<T> {
	
	Special getByShortcut(String shortcut);
	
}
