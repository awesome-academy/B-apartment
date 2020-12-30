package b.apartment.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import b.apartment.dao.FavouriteDAO;
import b.apartment.entity.Favourite;
import b.apartment.model.ApartmentModel;
import b.apartment.model.FavouriteModel;
import b.apartment.service.FavouriteService;


@Service
public class FavouriteServiceImp implements FavouriteService {

	private static Logger log = LoggerFactory.getLogger(FavouriteServiceImp.class);

	@Autowired
	private FavouriteDAO favouriteDAO;

	private FavouriteServiceImp() {
	}

	public void setFavouriteDao(FavouriteDAO favouriteDAO) {
		this.favouriteDAO = favouriteDAO;
	}
	
	
	@Transactional
	public FavouriteModel favourite(Integer userId, Integer apartmentId) throws Exception {
		try {
			Favourite condition = new Favourite();
			condition.setUserId(userId);
			condition.setApartmentId(apartmentId);
			Favourite favourite = favouriteDAO.makePersistent(condition);
			FavouriteModel favouriteModel = new FavouriteModel();
			BeanUtils.copyProperties(favourite, favouriteModel);
			return favouriteModel;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
		
	}
	
	
	public boolean checkFavourite(Integer userId, Integer apartmentId) throws Exception {
		log.info("Checking favourite in the database");
		boolean favourite = favouriteDAO.checkFavourite(userId, apartmentId);
		return favourite;
		}
	
	
	@Transactional(readOnly = true)
	public List<FavouriteModel> findFavouriteByUserId(Integer userId) throws Exception {
		try {
			log.info("Fetching favourite in the database");
			List<Favourite> favourites = favouriteDAO.findFavouriteByUserID(userId);
			List<FavouriteModel> favouriteModelList = new ArrayList<FavouriteModel>();
			for(Favourite favourite : favourites)
			{
				FavouriteModel favouriteModel = new FavouriteModel();
				BeanUtils.copyProperties(favourite, favouriteModel);
				
				ApartmentModel apartmentModel = new ApartmentModel();
				BeanUtils.copyProperties(favourite.getApartment(), apartmentModel);
				
				favouriteModel.setApartmentModel(apartmentModel);
				favouriteModelList.add(favouriteModel);
			}
			return favouriteModelList;
		}
		catch (Exception e) {
			log.error("Khong co favourite can tim trong Data Base", e);
			throw e;
		}
	}
}

