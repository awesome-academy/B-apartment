package b.apartment.service;

import java.util.List;

import b.apartment.model.ProjectsModel;
import org.springframework.data.domain.Page;

public interface ProjectService {
	public List<ProjectsModel> findAll();
	public Page<ProjectsModel> paginate(ProjectsModel projectsModel);
}
