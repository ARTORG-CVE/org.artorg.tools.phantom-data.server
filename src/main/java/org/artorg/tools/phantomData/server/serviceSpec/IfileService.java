package org.artorg.tools.phantomData.server.serviceSpec;

import org.artorg.tools.phantomData.server.model.base.DbFile;
import org.artorg.tools.phantomData.server.specification.DbPersistent;
import org.artorg.tools.phantomData.server.specification.IServiceDefault;

public interface IfileService<T extends DbPersistent<T,?>> extends IServiceDefault<T> {
	
	DbFile getByName(String name);
	
}
