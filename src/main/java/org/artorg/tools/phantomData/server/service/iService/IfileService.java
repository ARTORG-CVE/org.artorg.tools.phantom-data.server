package org.artorg.tools.phantomData.server.service.iService;

import org.artorg.tools.phantomData.server.model.PhantomFile;
import org.artorg.tools.phantomData.server.specification.DbPersistent;
import org.artorg.tools.phantomData.server.specification.IService;

public interface IfileService<T extends DbPersistent> extends IService<T> {
	
	PhantomFile getByName(String name);
	
}
