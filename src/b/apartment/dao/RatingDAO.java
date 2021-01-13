package b.apartment.dao;

import java.util.List;

import b.apartment.entity.Rating;

public interface RatingDAO extends GenericDAO<Rating, Integer> {
	
	public boolean isRating(Rating rating);
	
	public List<Rating> findRatingByApartmentId(Integer apartmentId);
	
	public Long countUserByApartmentId(Rating rating);
	
	public boolean checkRating(Integer userId, Integer apartmentId);
	
	public Double avgScore(Rating rating);

}
