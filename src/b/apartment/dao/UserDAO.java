package b.apartment.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import b.apartment.entity.Users;

import b.apartment.entity.Users;

public interface UserDAO extends GenericDAO<Users, Integer> {
	
	public Users findUserByEmail(String email);
	
}
