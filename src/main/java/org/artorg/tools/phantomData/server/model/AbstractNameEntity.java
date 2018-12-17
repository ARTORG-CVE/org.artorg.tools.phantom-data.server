package org.artorg.tools.phantomData.server.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractNameEntity<ITEM extends AbstractNameEntity<ITEM, T>,
	T extends Comparable<T>> extends AbstractPersonifiedEntity<ITEM> {
	private static final long serialVersionUID = -1258695249103231819L;

	public AbstractNameEntity() {}

	@Override
	public String toName() {
		return String.format("name: %s", getName());
	}

	@Override
	public String toString() {
		return String.format("%s [name=%s, %s]", getItemClass().getSimpleName(), getName(),
			super.toString());
	}

	@Override
	public int compareTo(ITEM that) {
		if (that == null) return -1;
		int result;
		result = getName().compareTo(that.getName());
		if (result != 0) return result;
		return super.compareTo(that);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!super.equals(obj)) return false;
		if (!(obj instanceof AbstractNameEntity)) return false;
		AbstractNameEntity<?, ?> other = (AbstractNameEntity<?, ?>) obj;
		if (getName() == null) {
			if (other.getName() != null) return false;
		} else if (!getName().equals(other.getName())) return false;
		return true;
	}

	// Getters & Setters
	public abstract T getName();
	
	public abstract void setName(T name);

}
