package org.artorg.tools.phantomData.server.specification;

import java.io.Serializable;
import java.util.UUID;

public interface DbPersistent<ITEM> extends Comparable<ITEM>, Serializable {
	
	UUID getId();
	
	void setId(UUID id);
	
	Class<ITEM> getItemClass();
	
	default UUID stringToID(String id) {
		return UUID.fromString(id);
	}
	
	default void setId(String id) {
		setId(UUID.fromString(id));
	}

}
