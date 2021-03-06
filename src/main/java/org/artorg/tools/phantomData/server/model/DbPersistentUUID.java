package org.artorg.tools.phantomData.server.model;

import java.util.UUID;

public interface DbPersistentUUID<T> extends DbPersistent<T,UUID> {
	
	default void setId(String value) {
		setId(UUID.fromString(value));
	}
	
	default String getStringId() {
		return getId().toString();
	}
	
}
