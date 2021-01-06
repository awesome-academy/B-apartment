package b.apartment.service.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import b.apartment.dao.RatingDAO;
import b.apartment.entity.Rating;
import b.apartment.model.ApartmentModel;
import b.apartment.model.RatingModel;
import b.apartment.model.UserModel;
import b.apartment.service.RatingService;

public class RatingServiceImp implements RatingService {
private static Logger log = LoggerFactory.getLogger(RatingServiceImp.class);
	
	@Autowired
	private RatingDAO ratingDAO;

	private RatingServiceImp() {
	}

	public void setRatingDao(RatingDAO ratingDAO) {
		this.ratingDAO = ratingDAO;
	}
	
	@Transactional
	public boolean rating(Integer apartment, Integer user, Integer score) throws Exception {
		log.info("Rating miropost into in the database");
		try {
			Rating condition = new Rating();
			condition.setApartmentId(apartment);
			condition.setUserId(user);
			condition.setScores(score);
			
			if(ratingDAO.isRating(condition)) {
				log.info("Apartments have been ranked in the database");
				return false;
			} else {
				log.info("Has successfully ranked the apartment");
				ratingDAO.makePersistent(condition);
				return true;
			}
		} catch (Exception e) {
			log.error("Error when rating the apartment", e);
			throw e;
		}
	}
}

