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
	public void favourite(Integer userId, Integer apartmentId) throws Exception {
		Favourite favourite = new Favourite();
		favourite.setUserId(userId);
		favourite.setApartmentId(apartmentId);
		favouriteDAO.makePersistent(favourite);
	}
	
	
	public boolean checkFavourite(Integer userId, Integer apartmentId) throws Exception {
		log.info("Checking favourite in the database");
		boolean favourite = favouriteDAO.checkFavourite(userId, apartmentId);
		return favourite;
		}
	
	@Transactional
	public Favourite findFavourite(Integer userId, Integer apartmentId)  {
		try {
			log.info("Checking favourite in the database");
			Favourite favourite = favouriteDAO.findFavourite(userId, apartmentId);
			favouriteDAO.makePersistent(favourite);
			return favourite;
		}
		catch (Exception e) {
			log.error("Khong co favourite can tim trong Data Base", e);
			return null;
		}
	}
	
	public List<FavouriteModel> findAll() throws Exception {
		log.info("Fetching all favourites in the database");
		List<FavouriteModel> favouriteModelList = new ArrayList<FavouriteModel>();
			List<Favourite> favouriteList = favouriteDAO.findAll();
			for (Favourite favourite : favouriteList) {
				FavouriteModel favouriteModel = new FavouriteModel();
				BeanUtils.copyProperties(favourite, favouriteModel);
				favouriteModelList.add(favouriteModel);
			}
			return favouriteModelList;
	}
}

