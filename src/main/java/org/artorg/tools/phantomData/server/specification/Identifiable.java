package org.artorg.tools.phantomData.server.specification;

public interface Identifiable<ID> {
	
	ID getId();
	
	void setId(ID id);

}