package b.apartment.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.cj.log.Log;

import b.apartment.dao.RatingDAO;
import b.apartment.entity.Favourite;
import b.apartment.entity.Rating;
import b.apartment.model.ApartmentModel;
import b.apartment.model.FavouriteModel;
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
	
	@Transactional(readOnly = true)
	public List<RatingModel> findRatingByApartmentId(Integer apartmentId) throws Exception {
		try {
			log.info("Fetching rating by apartment in the database");
			List<Rating> ratings = ratingDAO.findRatingByApartmentId(apartmentId);
			List<RatingModel> ratingModelList = new ArrayList<RatingModel>();
			for(Rating rating : ratings)
			{
				RatingModel ratingModel = new RatingModel();
				BeanUtils.copyProperties(rating, ratingModel);
				
				UserModel userModel = new UserModel();
				BeanUtils.copyProperties(rating.getUser(), userModel);
				
				ratingModel.setUserModel(userModel);
				ratingModelList.add(ratingModel);
			}
			return ratingModelList;
		}
		catch (Exception e) {
			log.error("Khong co rating can tim theo apartment trong Data Base", e);
			throw e;
		}
	}
	
	public Long countUserByApartmentId(ApartmentModel apartment) {
		log.info("Count the number of people who rated the apartment");
		Rating rating = new Rating();
		rating.setApartmentId(apartment.getId());
		return ratingDAO.countUserByApartmentId(rating);
	}
	
	public boolean checkRating(Integer userId, Integer apartmentId) throws Exception {
		log.info("Checking rating in the database");
		boolean rating = ratingDAO.checkRating(userId, apartmentId);
		return rating;
	}
	
	public Double avgScore(ApartmentModel apartmentModel) {
		log.info("Average score of the apartment");
		Rating rating = new Rating();
		rating.setApartmentId(apartmentModel.getId());
        return ratingDAO.avgScore(rating);     
	}

}

