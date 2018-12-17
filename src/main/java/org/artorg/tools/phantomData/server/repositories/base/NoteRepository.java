package org.artorg.tools.phantomData.server.repositories.base;

import java.util.UUID;

import org.artorg.tools.phantomData.server.models.base.Note;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "NOTES")
public interface NoteRepository extends CrudRepository<Note, UUID> {

}
