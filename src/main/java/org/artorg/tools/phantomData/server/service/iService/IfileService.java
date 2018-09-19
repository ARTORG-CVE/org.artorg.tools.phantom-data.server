package org.artorg.tools.phantomData.server.service.iService;

import org.artorg.tools.phantomData.server.model.PhantomFile;
import org.artorg.tools.phantomData.server.specification.DatabasePersistent;
import org.artorg.tools.phantomData.server.specification.IService;

public interface IfileService<T extends DatabasePersistent> extends IService<T> {
	
	PhantomFile getByName(String name);
	
}
