package org.artorg.tools.phantomData.server.serviceSpec;

import org.artorg.tools.phantomData.server.model.DbPersistent;
import org.artorg.tools.phantomData.server.models.base.DbFile;
import org.artorg.tools.phantomData.server.service.IServiceDefault;

public interface IfileService<T extends DbPersistent<T,?>> extends IServiceDefault<T> {
	
	DbFile getByName(String name);
	
}
