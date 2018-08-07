package org.artorg.tools.phantomData.server.service.iService;

import org.artorg.tools.phantomData.server.model.LiteratureBase;
import org.artorg.tools.phantomData.server.specification.DatabasePersistent;
import org.artorg.tools.phantomData.server.specification.IService;

public interface IliteratureBaseService<T extends DatabasePersistent<T,ID_TYPE>, ID_TYPE> extends IService<T, ID_TYPE> {
	
	LiteratureBase getByShortcut(String shortcut);
}
