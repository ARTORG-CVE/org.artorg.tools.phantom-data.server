package org.artorg.tools.phantomData.server.util;

import java.util.List;
import java.util.stream.Collectors;

public class EntityUtils {
	
	public static <E extends Comparable<E>> boolean equals(E e1, E e2) {
		if (e1 == null) {
			if (e2 != null) return false;
		} else if (!e1.equals(e2)) return false;
		return true;
	}
	
	public static int compare(boolean b1, boolean b2) {
		if (b1 == b2) return 0;
		if (b1) return 1;
		return -1;
	}
	
	public static <E extends Comparable<E>> int compare(List<E> one, List<E> two) {
		if (one == null) {
			if (two == null) {
				return 0;
			} else {
				return 1;
			}
		} else {
			if (two == null)
				return -1;
		}
			
		int result;
		result = Integer.compare(one.size(), two.size());
		if (result != 0) return result;
		
		one = one.stream().sorted().collect(Collectors.toList());
		two = two.stream().sorted().collect(Collectors.toList());
		for (int i=0; i<one.size(); i++) {
			if (one.get(i) == null)
				return 1;
			if (two.get(i) == null)
				return -1;
			result = one.get(i).compareTo(two.get(i));
			if (result != 0) return result;
		}
		return 0;
	}
	
	public static <E extends Comparable<E>> boolean equals(List<E> one, List<E> two) {
		if (one == null && two == null)
			return true;

		if ((one == null && two != null) || one != null && two == null
			|| one.size() != two.size())
			return false;

		one = one.stream().sorted().collect(Collectors.toList());
		two = two.stream().sorted().collect(Collectors.toList());
		return one.equals(two);
	}

}
