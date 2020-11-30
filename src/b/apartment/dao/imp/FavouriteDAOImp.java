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

import com.mysql.cj.log.Log;

import b.apartment.dao.FavouriteDAO;
import b.apartment.entity.Favourite;
import b.apartment.service.imp.FavouriteServiceImp;

@Repository
public class FavouriteDAOImp extends GenericDAOImp<Favourite, Integer> implements FavouriteDAO {
	private static Logger log = LoggerFactory.getLogger(FavouriteServiceImp.class);

	public FavouriteDAOImp() {
		super(Favourite.class);
	}
	
	public boolean checkFavourite(Integer userId, Integer apartmentId) {
		log.info("Checking favourite in the database");
		Long result = getHibernateTemplate().execute(new HibernateCallback<Long>() {
			 public Long doInHibernate(Session session) throws HibernateException {
					String sql = "SELECT COUNT(*) FROM Favourite WHERE userId = :userId AND apartmentId = :apartmentId";
		            Query<Long> query = session.createQuery(sql, Long.class);
					query.setParameter("userId", userId);
					query.setParameter("apartmentId", apartmentId);
					return query.uniqueResult();
				}
			});
			return result > 0;
		}
	
	public Favourite findFavourite(Integer userId, Integer apartmentId) {
		log.info("Finding favourite in the database");
		return getHibernateTemplate().execute(new HibernateCallback<Favourite>() {
			 public Favourite doInHibernate(Session session) throws HibernateException {
					String sql = "FROM Favourite WHERE userId = :userId AND apartmentId = :apartmentId";
		            Query<Favourite> query = session.createQuery(sql, Favourite.class);
					query.setParameter("userId", userId);
					query.setParameter("apartmentId", apartmentId);
					return query.uniqueResult();
				}
			});
		}



	
}
