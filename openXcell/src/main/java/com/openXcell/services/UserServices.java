package com.openXcell.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.openXcell.dao.UsersRepository;
import com.openXcell.model.User;

@Service
public class UserServices {

	@Autowired
	UsersRepository usersRepository;
	
	@Transactional(rollbackFor = Exception.class)
	public void saveUser(User user) {
		User u = usersRepository.save(user);
	} 
	
}
