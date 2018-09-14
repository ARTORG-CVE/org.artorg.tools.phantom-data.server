package org.artorg.tools.phantomData.server.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractIntegerIdentity extends AbstractIdentity<Integer> {
	
	public AbstractIntegerIdentity() {}
	
	@Override
	public Integer stringToID(String id) {
		return Integer.valueOf(id);
	}

}
