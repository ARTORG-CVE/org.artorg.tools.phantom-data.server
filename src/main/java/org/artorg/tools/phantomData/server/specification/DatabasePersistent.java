package org.artorg.tools.phantomData.server.specification;

import java.util.UUID;

public interface DatabasePersistent {
	
	public UUID getId();
	
	public void setId(UUID id);
	
	default UUID stringToID(String id) {
		return UUID.fromString(id);
	}
	
	default void setId(String id) {
		setId(UUID.fromString(id));
	}

}
