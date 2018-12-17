package org.artorg.tools.phantomData.server.serviceSpec.phantom;

import org.artorg.tools.phantomData.server.model.DbPersistent;
import org.artorg.tools.phantomData.server.models.phantom.LiteratureBase;
import org.artorg.tools.phantomData.server.service.IServiceDefault;

public interface IliteratureBaseService<T extends DbPersistent<T,?>> extends IServiceDefault<T> {
	
	LiteratureBase getByShortcut(String shortcut);
}
