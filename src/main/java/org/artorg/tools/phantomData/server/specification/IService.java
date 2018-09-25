package org.artorg.tools.phantomData.server.specification;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

public interface IService<T extends DbPersistent> {
	
	CrudRepository<T, UUID> getRepository();
	
	default List<T> getAll() {
		List<T> list = new ArrayList<>();
		getRepository().findAll().forEach(e -> list.add(e));
		return list;
	}
	
	default T getById(UUID id) {
		T obj = getRepository().findById(id).get();
		return obj;
	}
	
	default void update(T t) {
		getRepository().save(t);
		
	}
	
	default void delete(UUID id) {
		getRepository().delete(getById(id));
		
	}
	
	default boolean add(T t) {
		getRepository().save(t);
		return true;
		
//		List<AnnulusDiameter> list = annulusDiameterRepository.findByTitleAndCategory(annulusDiameter.getTitle(), annulusDiameter.getCategory()); 	
//        if (list.size() > 0) {
//           return false;
//        } else {
//        	annulusDiameterRepository.save(annulusDiameter);
//        return true;
	}

}
