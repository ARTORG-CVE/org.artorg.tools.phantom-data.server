package org.artorg.tools.phantomData.server.model;

public interface Identifiable<ID> {
	
	ID getId();
	
	void setId(ID id);
	
	default boolean equalsId(Identifiable<?> that) {
		return this.getId().toString().equals(that.getId().toString());
	}
	
	@SuppressWarnings("unchecked")
	default int compareToId(Identifiable<?> that) {
		return ((Comparable<ID>)this.getId()).compareTo((ID) that.getId());
	}

}