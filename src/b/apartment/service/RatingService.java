package b.apartment.service;

import b.apartment.model.ApartmentModel;
import b.apartment.model.RatingModel;
import b.apartment.model.UserModel;

public interface RatingService {
	
	public boolean rating(Integer apartment, Integer user, Integer score) throws Exception;
	
	

}
