package org.artorg.tools.phantomData.server.services.measurement;

import java.util.UUID;

import org.artorg.tools.phantomData.server.models.measurement.Project;
import org.artorg.tools.phantomData.server.repositories.measurement.ProjectRepository;
import org.artorg.tools.phantomData.server.service.IServiceDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectService implements IServiceDefault<Project> {

	@Autowired
	private ProjectRepository repository;
	
	@Override
	public CrudRepository<Project, UUID> getRepository() {
		return repository;
	}
	
}
