package com.revature.services;

import com.revature.repos.UserDAO;
import com.revature.repos.UserDAOImpl;
import com.revature.models.User;


public class UserService {

	private static UserDAO userDAO = new UserDAOImpl();
	
	
	public boolean approveReimbursement(User user, int reimbursementId) {
		return userDAO.approveReimbursement(user, reimbursementId);
	}
	
	public boolean denyReimbursement(User user, int reimbursementId) {
		return userDAO.denyReimbursement(user, reimbursementId);
	}
	
	public User getUserById(int userId) {
		return userDAO.findById(userId);
	}
	
	public User getUserByUserName(String username) {
		return userDAO.findByUserName(username);
	}
	
	
	public User userLogin(String username, String password) {
		return userDAO.login(username, password);
	}
}
