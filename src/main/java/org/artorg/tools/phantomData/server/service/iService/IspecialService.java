package org.artorg.tools.phantomData.server.service.iService;

import org.artorg.tools.phantomData.server.model.Special;
import org.artorg.tools.phantomData.server.specification.DbPersistent;
import org.artorg.tools.phantomData.server.specification.IService;

public interface IspecialService<T extends DbPersistent> extends IService<T> {
	
	Special getByShortcut(String shortcut);
	
}
