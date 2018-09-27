package org.artorg.tools.phantomData.server.specification;

import java.io.Serializable;

public interface DbPersistent<ITEM, ID> extends Identifiable<ID>, Comparable<ITEM>, Serializable {
	
	Class<ITEM> getItemClass();

}