package b.apartment.dao.imp;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;

import b.apartment.dao.RatingDAO;
import b.apartment.dao.imp.GenericDAOImp;
import b.apartment.entity.Favourite;
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
	
	public List<Rating> findRatingByApartmentId(Integer apartmentId) {
		logger.info("Search user who has rated the apartment from the database");
		try {
			return getHibernateTemplate().execute(new HibernateCallback<List<Rating>>() {
				 public List<Rating> doInHibernate(Session session) throws HibernateException {
						String sql = "FROM Rating WHERE apartmentId = :apartmentId";
			            Query<Rating> query = session.createQuery(sql, Rating.class);
						query.setParameter("apartmentId", apartmentId);
						return query.getResultList();
		}
			});
		} catch (Exception e) {
			return null;
		}
	}
	
	public Long countUserByApartmentId(Rating rating) {
		logger.info("count the number of people who rated the apartment");
		return getHibernateTemplate().execute(new HibernateCallback<Long>() {
			public Long doInHibernate(Session session) throws HibernateException {
				String sql = "SELECT COUNT(userId) FROM Rating WHERE apartmentId = :apartmentId";
				Query<Long> query = session.createQuery(sql, Long.class);
				query.setParameter("apartmentId", rating.getApartmentId());
				return query.uniqueResult();
			}
		}) ;
	}
	
	public boolean checkRating(Integer userId, Integer apartmentId) {
		log.info("Checking rating in the database");
		Long result = getHibernateTemplate().execute(new HibernateCallback<Long>() {
			 public Long doInHibernate(Session session) throws HibernateException {
					String sql = "SELECT COUNT(*) FROM Rating WHERE userId = :userId AND apartmentId = :apartmentId";
		            Query<Long> query = session.createQuery(sql, Long.class);
					query.setParameter("userId", userId);
					query.setParameter("apartmentId", apartmentId);
					return query.uniqueResult();
				}
			});
			return result > 0;
		}
	
	public Double avgScore(Rating rating) {
		logger.info("Average score of the apartment");
		return getHibernateTemplate().execute(new HibernateCallback<Double>() {
			public Double doInHibernate(Session session) throws HibernateException {
				String sql = "SELECT AVG(scores) FROM Rating WHERE apartmentId = :apartmentId";
				Query<Double> query = session.createQuery(sql, Double.class);
				query.setParameter("apartmentId", rating.getApartmentId());
				return query.uniqueResult();
			}
		}) ;
	}

}
