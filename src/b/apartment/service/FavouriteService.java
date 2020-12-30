package b.apartment.service;

import java.util.List;

import b.apartment.entity.Favourite;
import b.apartment.model.FavouriteModel;


public interface FavouriteService  {

	public FavouriteModel favourite(Integer userId, Integer apartmentId) throws Exception;
	
	public boolean checkFavourite(Integer userId, Integer apartmentId) throws Exception;
	
	public List<FavouriteModel> findFavouriteByUserId(Integer userId) throws Exception;
	
}
