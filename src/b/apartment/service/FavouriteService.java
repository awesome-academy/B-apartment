package b.apartment.service;

import java.util.List;

import b.apartment.entity.Favourite;
import b.apartment.model.FavouriteModel;


public interface FavouriteService  {

	public void favourite(Integer userId, Integer apartmentId) throws Exception;
	
	public boolean checkFavourite(Integer userId, Integer apartmentId) throws Exception;
	
	public Favourite findFavourite(Integer userId, Integer apartmentId) throws Exception;
	
	public List<FavouriteModel> findAll() throws Exception;
	
}
