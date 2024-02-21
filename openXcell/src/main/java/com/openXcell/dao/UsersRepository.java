package com.openXcell.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openXcell.model.User;

public interface UsersRepository extends JpaRepository<User, Long> {
	  User findByEmail(String username);
}