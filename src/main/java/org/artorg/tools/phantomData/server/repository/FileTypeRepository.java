package org.artorg.tools.phantomData.server.repository;

import org.artorg.tools.phantomData.server.model.FileType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "FILE_TYPES")
public interface FileTypeRepository extends CrudRepository<FileType, Integer> {

}
