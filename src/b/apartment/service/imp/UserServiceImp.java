package b.apartment.service.imp;

import java.util.ArrayList;
import java.util.Date;
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

import b.apartment.dao.UserDAO;
import b.apartment.entity.Role;
import b.apartment.entity.Users;
import b.apartment.model.CustomUserDetails;
import b.apartment.model.UserModel;
import b.apartment.service.UserService;

@Service
public class UserServiceImp implements UserService {

	private static Logger log = LoggerFactory.getLogger(UserServiceImp.class);

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	private UserServiceImp() {
	}

	public void setUserDao(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public UserModel findUserByEmail(String email) {
		log.info("Fetching the user by email in the database");
		try {
			Users user = userDAO.findUserByEmail(email);
			UserModel userModel = null;
			if (user != null) {
				userModel = new UserModel();
				BeanUtils.copyProperties(user, userModel);
			}
			return userModel;
		} catch (Exception e) {
			log.error("An error occurred while fetching the user details by email from the database", e);
			return null;
		}
	}

	

	@Transactional
	public UserModel addUser(UserModel userModel) throws Exception {
		log.info("Adding the user in the database");
		try {
			Users condition = new Users();
			condition.setId(userModel.getId());
			condition.setName(userModel.getName());
			condition.setEmail(userModel.getEmail());
			condition.setPassword(passwordEncoder.encode(userModel.getPassword()));
			condition.setRole(Role.USER_ROLE);
			condition.setAge(userModel.getAge());
			condition.setPhone(userModel.getPhone());
			condition.setGender(userModel.getGender());
			Users user = userDAO.makePersistent(condition);
			userModel = new UserModel();
			BeanUtils.copyProperties(user, userModel);
			return userModel;
		} catch (Exception e) {
			log.error("An error occurred while adding the user details to the database", e);
			throw e;
		}
	}

	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Users user = userDAO.findUserByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new CustomUserDetails(user);
	}

	@Override
	public void createNewToken(PersistentRememberMeToken token) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateToken(String series, String tokenValue, Date lastUsed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PersistentRememberMeToken getTokenForSeries(String seriesId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeUserTokens(String username) {
		// TODO Auto-generated method stub
		
	}
}
