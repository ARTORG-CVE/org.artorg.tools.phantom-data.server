package org.artorg.tools.phantomData.server.specification;

public interface DatabasePersistent<ID_TYPE> {
	public ID_TYPE getId();

	public void setId(ID_TYPE id);

	public ID_TYPE stringToID(String id);

}
