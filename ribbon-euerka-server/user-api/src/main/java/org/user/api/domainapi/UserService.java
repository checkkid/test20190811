package org.user.api.domainapi;

import java.util.List;

import org.user.api.domain.User;

public interface UserService {

	
	boolean saveUser(User user);
	
	List<User> findAll();
	
	
}
