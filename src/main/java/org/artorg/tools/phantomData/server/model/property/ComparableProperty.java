package org.artorg.tools.phantomData.server.model.property;

public interface ComparableProperty<T extends Property<U>, U extends Comparable<U>> extends Comparable<T> {
	
	@SuppressWarnings("unchecked")
	default int compareTo(T that) {
		int i = ((Property<U>)this).getPropertyField().compareTo(that.getPropertyField());
		if (i != 0) return i;
		i = ((Property<U>)this).getValue().compareTo(that.getValue());
		return i;
	}

}
