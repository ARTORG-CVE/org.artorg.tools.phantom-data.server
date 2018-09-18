package org.artorg.tools.phantomData.server.specification;

import java.util.function.Function;

public abstract class AbstractIdentity {
	private Class<?> itemClass;
//	private Function<String,Object> toItemTypeMapper;
	private Function<Object, String> toStringMapper;
	
	public Class<?> getItemClass() {
		return itemClass;
	}

	protected void setItemClass(Class<?> itemClass) {
		this.itemClass = itemClass;
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Object> AbstractIdentity(Class<T> itemClass, 
//			Function<String,T> toItemTypeMapper, 
			Function<T, String> toStringMapper) {
		this.itemClass = itemClass;
//		this.toItemTypeMapper = s -> toItemTypeMapper.apply(s);
		this.toStringMapper = (Object o) -> toStringMapper.apply((T) o);
	}

//	public void setStringId(String s) {
//		setId(toItemTypeMapper.apply(s));
//	}
	
	public String getStringId() {
		return toStringMapper.apply(getId());
	}
	
//	public abstract void setId(Object o);
	
	public abstract Object getId();

}
