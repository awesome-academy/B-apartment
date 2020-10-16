package b.apartment.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import b.apartment.model.UserModel;

public interface UserService extends UserDetailsService, PersistentTokenRepository {

	public UserModel addUser(UserModel user) throws Exception;
	
	public UserModel findUserByEmail(String email);

}
