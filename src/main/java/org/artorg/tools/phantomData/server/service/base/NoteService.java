package org.artorg.tools.phantomData.server.service.base;

import java.util.UUID;

import org.artorg.tools.phantomData.server.model.base.Note;
import org.artorg.tools.phantomData.server.repository.base.NoteRepository;
import org.artorg.tools.phantomData.server.specification.IServiceDefault;
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
