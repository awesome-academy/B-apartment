package b.apartment.dao.imp;

import java.util.List;

import b.apartment.model.ApartmentModel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

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

	public List<Apartments> apartmentsHot() {
		try {
			return getHibernateTemplate().execute(new HibernateCallback<List<Apartments>>() {
				public List<Apartments> doInHibernate(Session session) throws HibernateException {
					Query<Apartments> query = session.createQuery("FROM Apartments");
					return query.list();
				}
			});
		} catch (Exception e) {
			log.error("Khong tim thay can ho nao hot ca.", e);
			return null;
		}
	}

	public List<Apartments> apartmentsByProvince(ApartmentModel apartmentModel) {
		try {
			return getHibernateTemplate().execute(new HibernateCallback<List<Apartments>>() {
				public List<Apartments> doInHibernate(Session session) throws HibernateException {
					Query<Apartments> query = session.createQuery("SELECT a FROM Apartments a JOIN a.project p WHERE p.province = :provinceId");
					query.setParameter("provinceId", apartmentModel.getProvinceId());
					return query.list();
				}
			});
		} catch (Exception e) {
			log.error("Khong tim thay can ho nao hot ca.", e);
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
	
	public List<Apartments> searchApartments(Apartments searchA) {
		log.info("Tim kiem can ho theo tu khoa");
		try {
			return getHibernateTemplate().execute(new HibernateCallback<List<Apartments>>() {
				public List<Apartments> doInHibernate(Session session) throws HibernateException {
					Query<Apartments> query = session.createQuery("FROM Apartments WHERE LOWER(name) LIKE :search");
					query.setParameter("search", "%"+ searchA.getName().toLowerCase() +"%");
					return query.list();
				}
			});
		}
		catch (Exception e) {
			log.error("Loi khi tim kiem can ho theo tu khoa", e);
			return null;
		}
	}
}
