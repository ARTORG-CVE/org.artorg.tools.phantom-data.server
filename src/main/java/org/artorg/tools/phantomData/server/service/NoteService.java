package org.artorg.tools.phantomData.server.service;

import java.util.UUID;

import org.artorg.tools.phantomData.server.model.Note;
import org.artorg.tools.phantomData.server.repository.NoteRepository;
import org.artorg.tools.phantomData.server.specification.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class NoteService implements IService<Note> {

	@Autowired
	private NoteRepository repository;
	
	@Override
	public CrudRepository<Note, UUID> getRepository() {
		return repository;
	}

}
