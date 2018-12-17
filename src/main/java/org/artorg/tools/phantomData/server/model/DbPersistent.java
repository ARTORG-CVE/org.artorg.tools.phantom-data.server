package org.artorg.tools.phantomData.server.model;

import java.io.Serializable;

public interface DbPersistent<ITEM, ID extends Comparable<ID>> extends Identifiable<ID>, Comparable<ITEM>, Serializable {
	
	Class<ITEM> getItemClass();

	void setId(String value);
	
	String getStringId();

}