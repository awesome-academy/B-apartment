package b.apartment.dao.imp;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;

import b.apartment.dao.ApartmentDAO;
import b.apartment.entity.Apartments;
import b.apartment.service.imp.UserServiceImp;
import b.apartment.util.CommonUtil;
import b.apartment.util.SearchQueryTemplate;

@Repository
public class ApartmentDAOImp extends GenericDAOImp<Apartments, Integer> implements ApartmentDAO {
	private static Logger log = LoggerFactory.getLogger(UserServiceImp.class);

	public ApartmentDAOImp() {
		super(Apartments.class);
	}
	
	public Apartments findApartment(Apartments apartments) {
		log.info("Tim can ho trong co so du lieu");
		try {
			List<Apartments> apartmentList = findAll();
			if (!CommonUtil.isEmpty(apartmentList)) {
				return apartmentList.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			log.error("An error occurred while fetching the apartment details from the database", e);
			return null;
		}
	}
	
	@Override
	public Page<Apartments> paginate(Apartments apartments, Pageable pageable) {
		log.info("Phan trang cac can ho trong co so du lieu");
		try {
			
			String sql = "FROM Apartments";
			String countSql = "SELECT COUNT(*) FROM Apartments";
			SearchQueryTemplate searchQueryTemplate = new SearchQueryTemplate(sql, countSql, pageable);
			return paginate(searchQueryTemplate);
		} catch (Exception e) {
			log.error("An error occurred while paging the apartment details from the database", e);
			return null;
		}
	}
	
	public List<Apartments> findApartmentByProjectId(int project_id) {
		log.info("Tim can ho theo projectId trong co so du lieu");
		try {
			return getHibernateTemplate().execute(new HibernateCallback<List<Apartments>>() {
				public List<Apartments> doInHibernate(Session session) throws HibernateException {
					Query<Apartments> query = session.createQuery("FROM Apartments WHERE project_id = :projectId");
					query.setParameter("projectId", project_id);
					return query.list();
				}
			});
		} catch (Exception e) {
			log.error("An error occurred while fetching the apartment details by project_id from the database", e);
			return null;
		}
	}
	
}