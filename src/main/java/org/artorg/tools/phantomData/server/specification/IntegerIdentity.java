package org.artorg.tools.phantomData.server.specification;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class IntegerIdentity extends AbstractIdentity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Integer id;
	
	public IntegerIdentity() {
		super(Integer.class, i -> Integer.toString(i));
	}

	@Override
	public Integer getId() {
		return id;
		
	}


	
	
	

//	
//	
//	public AbstractIdentity() {}
//	
//	public T getId() {
//		return id;
//	}
//	
//	public void setId(T id) {
//		this.id = id;
//	}

}
