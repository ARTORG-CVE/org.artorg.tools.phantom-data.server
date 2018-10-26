package org.artorg.tools.phantomData.server.service.iService;

import org.artorg.tools.phantomData.server.model.DbFile;
import org.artorg.tools.phantomData.server.specification.DbPersistent;
import org.artorg.tools.phantomData.server.specification.IService;

public interface IfileService<T extends DbPersistent<T,?>> extends IService<T> {
	
	DbFile getByName(String name);
	
}
