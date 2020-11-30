package b.apartment.dao;

import b.apartment.entity.Favourite;

public interface FavouriteDAO extends GenericDAO<Favourite, Integer> {
	
	public boolean checkFavourite(Integer userId, Integer apartmentId);
	
	public Favourite findFavourite(Integer userId, Integer apartmentId);

}
