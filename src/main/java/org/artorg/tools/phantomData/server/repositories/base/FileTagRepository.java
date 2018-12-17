package org.artorg.tools.phantomData.server.repositories.base;

import java.util.UUID;

import org.artorg.tools.phantomData.server.models.base.FileTag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "FILE_TAGS")
public interface FileTagRepository extends CrudRepository<FileTag, UUID> {

}