package com.simplehomeinsurance.claims_management_system.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplehomeinsurance.claims_management_system.dao.UserDAO;
import com.simplehomeinsurance.claims_management_system.entity.User;

@Service
public class UserService {	
	@Autowired	
	private UserDAO userDAO;
	
	public UserService(UserDAO theUserDAO) {
		userDAO = theUserDAO;
	}
	
	@Transactional
	public User getUser(int userId) {		
		return userDAO.getUser(userId);		
	}
	
	@Transactional
	public User getUserbyUsername(String username) {	
		return userDAO.getUserbyUsername(username);
	}
	
	@Transactional
	public List<User> getUserList() {
		return userDAO.getUserList();
	}
	
}
