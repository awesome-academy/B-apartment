package b.apartment.model;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import b.apartment.entity.Role;
import b.apartment.entity.Users;

@SuppressWarnings("serial")
public class CustomUserDetails extends org.springframework.security.core.userdetails.User {
	private Users users = null;

	public CustomUserDetails(Users users) {
		super(users.getEmail(), users.getPassword(), mapRolesToAuthorities(users.getRole()));
		this.users = users;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	private static Collection<? extends GrantedAuthority> mapRolesToAuthorities(int value) {
		return Role.toList(value).stream().map(role -> new SimpleGrantedAuthority(role.name()))
				.collect(Collectors.toList());
	}

}
