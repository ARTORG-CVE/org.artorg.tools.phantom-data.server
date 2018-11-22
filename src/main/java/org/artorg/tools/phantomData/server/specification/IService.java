package org.artorg.tools.phantomData.server.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface IService<T> {
	
	CrudRepository<T, ?> getRepository();
	
	default List<T> getAll() {
		List<T> list = new ArrayList<>();
		getRepository().findAll().forEach(e -> list.add(e));
		return list;
	}
	
	@SuppressWarnings("unchecked")
	default <U extends Identifiable<ID>, ID extends Comparable<ID>> U getById(ID id) {
		U obj = ((CrudRepository<U,ID>)getRepository()).findById(id).get();
		return obj;
	}
	
	default void update(T t) {
		getRepository().save(t);
		
	}
	
	default <U extends Identifiable<ID>, ID extends Comparable<ID>> void delete(ID id) {
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
	
	@SuppressWarnings("unchecked")
	default <U extends Identifiable<ID>, ID extends Comparable<ID>> Boolean existById(ID id) {
		return ((CrudRepository<U,ID>)getRepository()).existsById(id);
	}

}
