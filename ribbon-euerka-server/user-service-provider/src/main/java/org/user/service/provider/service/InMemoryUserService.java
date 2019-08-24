package org.user.service.provider.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;
import org.user.api.domain.User;
import org.user.api.domainapi.UserService;

@Service
public class InMemoryUserService implements UserService{

	
	private Map<Long,User> repository = new ConcurrentHashMap<>();
	
	
	@Override
	public boolean saveUser(User user) {
		// TODO Auto-generated method stub
		return repository.put(user.getId(), user)==null;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return new ArrayList<>(repository.values());
	}
	
	

}
