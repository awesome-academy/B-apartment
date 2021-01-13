package b.apartment.service;

import java.util.List;

import b.apartment.model.ApartmentModel;
import b.apartment.model.RatingModel;
import b.apartment.model.UserModel;


public interface RatingService {
	
	public boolean rating(Integer apartment, Integer user, Integer score) throws Exception;
	
	public List<RatingModel> findRatingByApartmentId(Integer apartmentId) throws Exception;
	
	public Long countUserByApartmentId(ApartmentModel apartment);
	
	public boolean checkRating(Integer userId, Integer apartmentId) throws Exception;
	
	public Double avgScore(ApartmentModel apartmentModel);

}
