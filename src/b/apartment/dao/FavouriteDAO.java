package b.apartment.dao;

import java.util.List;

import b.apartment.entity.Favourite;

public interface FavouriteDAO extends GenericDAO<Favourite, Integer> {
	
	public boolean checkFavourite(Integer userId, Integer apartmentId);
	
	public List<Favourite> findFavouriteByUserID(Integer userId);

}
