package org.artorg.tools.phantomData.server.model.property;

public interface ComparableProperty<T extends Property<U, ID_TYPE>, U extends Comparable<U>, ID_TYPE> extends Comparable<T> {
	
	@SuppressWarnings("unchecked")
	default int compareTo(T that) {
		int i = ((Property<U, ID_TYPE>)this).getPropertyField().compareTo(that.getPropertyField());
		if (i != 0) return i;
		i = ((Property<U, ID_TYPE>)this).getValue().compareTo(that.getValue());
		return i;
	}

}
