package org.artorg.tools.phantomData.server.specification;

public interface Identifiable<ID extends Comparable<ID>> {
	
	ID getId();
	
	void setId(ID id);
	
	default boolean equalsId(Identifiable<ID> that) {
		return this.getId().equals(that.getId());
	}
	
	default int compareToId(Identifiable<ID> that) {
		return this.getId().compareTo(that.getId());
	}

}