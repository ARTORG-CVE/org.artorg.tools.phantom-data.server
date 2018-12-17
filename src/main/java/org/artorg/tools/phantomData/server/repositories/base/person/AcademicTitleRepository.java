package org.artorg.tools.phantomData.server.repositories.base.person;

import java.util.UUID;

import org.artorg.tools.phantomData.server.models.base.person.AcademicTitle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "ACADEMIC_TITLES")
public interface AcademicTitleRepository extends CrudRepository<AcademicTitle, UUID> {

}
