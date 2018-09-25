package org.artorg.tools.phantomData.server.specification;

import java.io.Serializable;
import java.util.UUID;

public interface DbPersistent<ITEM> extends Comparable<ITEM>, Serializable {
	
	public UUID getId();
	
	public void setId(UUID id);
	
	default UUID stringToID(String id) {
		return UUID.fromString(id);
	}
	
	default void setId(String id) {
		setId(UUID.fromString(id));
	}

}
