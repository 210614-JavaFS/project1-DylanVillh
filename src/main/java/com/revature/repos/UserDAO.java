package com.revature.repos;

import com.revature.models.User;

public interface UserDAO {

	public User findById(int userId);
	
	public User findByUserName(String userName);
	
	public boolean approveReimbursement(User user, int reimbId);
	
	public boolean denyReimbursement(User user, int reimbId);

	public User login(String username, String password);
	
}
