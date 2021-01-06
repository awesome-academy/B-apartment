package b.apartment.dao.imp;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;

import b.apartment.dao.RatingDAO;
import b.apartment.dao.imp.GenericDAOImp;
import b.apartment.entity.Rating;
import b.apartment.service.imp.RatingServiceImp;

@Repository
public class RatingDAOImp extends GenericDAOImp<Rating, Integer> implements RatingDAO {
	private static Logger log = LoggerFactory.getLogger(RatingServiceImp.class);

	public RatingDAOImp() {
		super(Rating.class);
	}
	
	public boolean isRating(Rating rating) {
		log.info("Checking rating in the database");
		Long result = getHibernateTemplate().execute(new HibernateCallback<Long>() {
			 public Long doInHibernate(Session session) throws HibernateException {
					String sql = "SELECT COUNT(*) FROM Rating WHERE userId = :userId AND apartmentId = :apartmentId";
		            Query<Long> query = session.createQuery(sql, Long.class);
					query.setParameter("userId", rating.getUserId());
					query.setParameter("apartmentId", rating.getApartmentId());
					return query.uniqueResult();
				}
			});
			return result > 0;
	}

}
