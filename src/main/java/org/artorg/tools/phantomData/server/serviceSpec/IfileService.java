package org.artorg.tools.phantomData.server.serviceSpec;

import org.artorg.tools.phantomData.server.model.base.DbFile;
import org.artorg.tools.phantomData.server.specification.DbPersistent;
import org.artorg.tools.phantomData.server.specification.IService;

public interface IfileService<T extends DbPersistent<T,?>> extends IService<T> {
	
	DbFile getByName(String name);
	
}
