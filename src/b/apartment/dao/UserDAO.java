package b.apartment.dao;

import b.apartment.entity.Users;

public interface UserDAO extends GenericDAO<Users, Integer> {
	
	public Users findUserByEmail(String email);
	
	public Users findUser(Users user);
	
}
