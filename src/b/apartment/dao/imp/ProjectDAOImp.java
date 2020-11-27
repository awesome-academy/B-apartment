package b.apartment.dao.imp;

import java.util.List;

import b.apartment.entity.Apartments;
import b.apartment.model.ApartmentModel;
import b.apartment.service.imp.ProjectServiceImp;
import b.apartment.util.SearchQueryTemplate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import b.apartment.dao.ProjectDAO;
import b.apartment.entity.Projects;
import b.apartment.service.imp.ApartmentServiceImp;
import b.apartment.util.CommonUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.hibernate5.HibernateCallback;

public class ProjectDAOImp extends GenericDAOImp<Projects, Integer> implements ProjectDAO {
	
	private static Logger log = LoggerFactory.getLogger(ProjectServiceImp.class);

	public ProjectDAOImp() {
		super(Projects.class);
	}

	@Override
	public Page<Projects> paginate(Projects projects, Pageable pageable) {
		log.info("Paging the apartments in the database");
		try {
			String sql = "FROM Projects";
			String countSql = "SELECT COUNT(*) FROM Projects";
			SearchQueryTemplate searchQueryTemplate = new SearchQueryTemplate(sql, countSql, pageable);
			return paginate(searchQueryTemplate);
		} catch (Exception e) {
			log.error("An error occurred while paging the apartment details from the database", e);
			return null;
		}
	}

	public List<Projects> allProject() {
		try {
			return getHibernateTemplate().execute(new HibernateCallback<List<Projects>>() {
				public List<Projects> doInHibernate(Session session) throws HibernateException {
					Query<Projects> query = session.createQuery("FROM Projects");
					return query.list();
				}
			});
		} catch (Exception e) {
			log.error("Khong tim thay can ho nao hot ca.", e);
			return null;
		}
	}
}
