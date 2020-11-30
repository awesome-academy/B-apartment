package b.apartment.service.imp;

import java.util.ArrayList;
import java.util.List;

import b.apartment.entity.Apartments;
import b.apartment.model.ApartmentModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import b.apartment.dao.ProjectDAO;
import b.apartment.entity.Projects;
import b.apartment.model.ProjectsModel;
import b.apartment.service.ProjectService;
import org.springframework.data.domain.Page;

public class ProjectServiceImp implements ProjectService {
	
	private static Logger log = LoggerFactory.getLogger(ProjectServiceImp.class);
	
	@Autowired
	private ProjectDAO projectDAO;
	
	public void setProjectDao(ProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}
	
	public List<ProjectsModel> findAll() {
        log.info("Fetching all projects in the database");
        List<ProjectsModel> projectsModelList = new ArrayList<ProjectsModel>();
        try {
            List<Projects> projectList = projectDAO.findAll();
            for (Projects projects : projectList) {
            	ProjectsModel projectModel = new ProjectsModel();
                BeanUtils.copyProperties(projects, projectModel);
                projectsModelList.add(projectModel);
            }
        } catch (Exception e) {
            log.error("An error occurred while fetching all projects from the database", e);
        }
        return projectsModelList;
    }

    @Override
    public Page<ProjectsModel> paginate(ProjectsModel projectsModel) {
        try {
            Projects condition = new Projects();
            Page<Projects> projects = projectDAO.paginate(condition, projectsModel.getPageable());
            return projects.map(project -> {
                ProjectsModel model = new ProjectsModel();
                BeanUtils.copyProperties(project, model);
                return model;
            });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

}
