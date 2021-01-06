package b.apartment.dao;

import b.apartment.entity.Rating;

public interface RatingDAO extends GenericDAO<Rating, Integer> {
	
	public boolean isRating(Rating rating);

}
