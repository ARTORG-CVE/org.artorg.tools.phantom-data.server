package org.artorg.tools.phantomData.server.service.iService;

import org.artorg.tools.phantomData.server.model.Special;
import org.artorg.tools.phantomData.server.specification.DatabasePersistent;
import org.artorg.tools.phantomData.server.specification.IService;

public interface IspecialService<T extends DatabasePersistent> extends IService<T> {
	
	Special getByShortcut(String shortcut);
	
}
