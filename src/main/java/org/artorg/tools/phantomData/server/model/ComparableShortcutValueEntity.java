package org.artorg.tools.phantomData.server.model;

public interface ComparableShortcutValueEntity <T extends AbstractShortcutValueEntity<T,U,V>, U extends Comparable<U>, V extends Comparable<V>> extends Comparable<T> {

	@SuppressWarnings("unchecked")
	default  int compareTo(T that) {
		return ((AbstractShortcutValueEntity<T,U,V>)this).getShortcut().compareTo(
				((AbstractShortcutValueEntity<T,U,V>)that).getShortcut());
	}
	
}
