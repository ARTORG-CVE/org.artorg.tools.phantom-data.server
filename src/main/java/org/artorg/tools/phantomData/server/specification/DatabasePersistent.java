package org.artorg.tools.phantomData.server.specification;

public interface DatabasePersistent<T extends DatabasePersistent<T, ID_TYPE>, ID_TYPE> {
	public ID_TYPE getId();

	public void setId(ID_TYPE id);
	
	public HttpDatabaseCrud<T, ID_TYPE> getConnector();
	
	@SuppressWarnings("unchecked")
	default void dbCreate() {
		getConnector().create((T) this);
	}
	
	@SuppressWarnings("unchecked")
	default void dbDelete() {
		getConnector().delete((T)this);
	}
	
	public ID_TYPE stringToID(String id);

}
