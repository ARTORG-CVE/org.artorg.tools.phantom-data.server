package org.artorg.tools.phantomData.server.services.base;

import java.util.UUID;

import org.artorg.tools.phantomData.server.models.base.Note;
import org.artorg.tools.phantomData.server.repositories.base.NoteRepository;
import org.artorg.tools.phantomData.server.service.IServiceDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class NoteService implements IServiceDefault<Note> {

	@Autowired
	private NoteRepository repository;
	
	@Override
	public CrudRepository<Note, UUID> getRepository() {
		return repository;
	}

}
