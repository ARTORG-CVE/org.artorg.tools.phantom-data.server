package org.artorg.tools.phantomData.server.service.iService;

import org.artorg.tools.phantomData.server.model.LiteratureBase;
import org.artorg.tools.phantomData.server.specification.DatabasePersistent;
import org.artorg.tools.phantomData.server.specification.IService;

public interface IliteratureBaseService<T extends DatabasePersistent> extends IService<T> {
	
	LiteratureBase getByShortcut(String shortcut);
}
