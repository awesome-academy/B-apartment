package b.apartment.dao.imp;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import b.apartment.dao.ProjectDAO;
import b.apartment.entity.Projects;
import b.apartment.service.imp.ApartmentServiceImp;
import b.apartment.util.CommonUtil;

public class ProjectDAOImp extends GenericDAOImp<Projects, Integer> implements ProjectDAO {
	
	private static Logger log = LoggerFactory.getLogger(ApartmentServiceImp.class);

	public ProjectDAOImp() {
		super(Projects.class);
	}
}
