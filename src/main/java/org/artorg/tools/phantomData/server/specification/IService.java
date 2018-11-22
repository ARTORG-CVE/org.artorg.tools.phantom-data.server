package org.artorg.tools.phantomData.server.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface IService<T,ID> {
	
	CrudRepository<T, ID> getRepository();
	
	default List<T> getAll() {
		List<T> list = new ArrayList<>();
		getRepository().findAll().forEach(e -> list.add(e));
		return list;
	}
	
	default T getById(ID id) {
		System.out.println("IService: " +id.getClass().getSimpleName());
		
		T obj = getRepository().findById(id).get();
		return obj;
	}
	
	default void update(T t) {
		getRepository().save(t);
		
	}
	
	default void delete(ID id) {
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
	
	default Boolean existById(ID id) {
		return getRepository().existsById(id);
	}

}
