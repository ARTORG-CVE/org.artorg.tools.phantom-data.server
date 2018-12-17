package org.artorg.tools.phantomData.server.repositories.base.person;

import java.util.UUID;

import org.artorg.tools.phantomData.server.models.base.person.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "PERSONS")
public interface PersonRepository extends CrudRepository<Person, UUID> {

}
