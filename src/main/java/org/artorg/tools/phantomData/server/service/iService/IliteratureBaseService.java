package org.artorg.tools.phantomData.server.service.iService;

import org.artorg.tools.phantomData.server.model.LiteratureBase;
import org.artorg.tools.phantomData.server.specification.DbPersistent;
import org.artorg.tools.phantomData.server.specification.IService;

public interface IliteratureBaseService<T extends DbPersistent<T,?>> extends IService<T> {
	
	LiteratureBase getByShortcut(String shortcut);
}
