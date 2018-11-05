package org.artorg.tools.phantomData.server.serviceSpec.phantom;

import org.artorg.tools.phantomData.server.model.phantom.LiteratureBase;
import org.artorg.tools.phantomData.server.specification.DbPersistent;
import org.artorg.tools.phantomData.server.specification.IService;

public interface IliteratureBaseService<T extends DbPersistent<T,?>> extends IService<T> {
	
	LiteratureBase getByShortcut(String shortcut);
}
