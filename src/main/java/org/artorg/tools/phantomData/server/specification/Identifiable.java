package org.artorg.tools.phantomData.server.specification;

public interface Identifiable<ID extends Comparable<ID>> {
	
	ID getId();
	
	void setId(ID id);
	
	default boolean equals(Identifiable<ID> that) {
		return this.getId().equals(that.getId());
	}
	
	default int compareTo(Identifiable<ID> that) {
		return this.getId().compareTo(that.getId());
	}

}