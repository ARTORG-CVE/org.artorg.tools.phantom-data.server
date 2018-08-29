package org.artorg.tools.phantomData.server.service.iService.property;

import org.artorg.tools.phantomData.server.specification.DatabasePersistent;
import org.artorg.tools.phantomData.server.specification.IService;

public interface IpropertyContainerService<T extends DatabasePersistent<ID_TYPE>, ID_TYPE> extends IService<T, ID_TYPE> {

}
