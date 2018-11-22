package org.artorg.tools.phantomData.server.serviceSpec.phantom;

import org.artorg.tools.phantomData.server.model.phantom.LiteratureBase;
import org.artorg.tools.phantomData.server.specification.DbPersistent;
import org.artorg.tools.phantomData.server.specification.IServiceDefault;

public interface IliteratureBaseService<T extends DbPersistent<T,?>> extends IServiceDefault<T> {
	
	LiteratureBase getByShortcut(String shortcut);
}
