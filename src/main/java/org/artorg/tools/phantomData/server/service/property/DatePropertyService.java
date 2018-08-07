package org.artorg.tools.phantomData.server.service.property;

import org.artorg.tools.phantomData.server.model.property.DateProperty;
import org.artorg.tools.phantomData.server.repository.property.DatePropertyRepository;
import org.artorg.tools.phantomData.server.specification.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class DatePropertyService implements IService<DateProperty, Integer> {

	@Autowired
	private DatePropertyRepository datePropertyFieldRepository;
	
	@Override
	public CrudRepository<DateProperty, Integer> getRepository() {
		return datePropertyFieldRepository;
	}

}
