package com.revature.repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class UserDAOImpl implements UserDAO {
		
		UserRoleDAO userRoleDAO = new UserRoleDAOImpl();
		

		@Override
		public boolean approveReimbursement(User user, int reimbId) {
			
			try(Connection conn = ConnectionUtil.getConnection()){
				
				String sql = "UPDATE ers_reimbursement SET reimb_resolved = CURRENT_TIMESTAMP, reimb_status_id = ?, reimb_resolver = ?"
						+ "WHERE reimb_id = ?;";
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setInt(1, 2);
				statement.setInt(2, user.getUserId());
				statement.setInt(3, reimbId);
				statement.execute();

				return true;
			}catch(SQLException e){
				e.printStackTrace();
			}
			return false;
		}

		@Override
		public boolean denyReimbursement(User user, int reimbId) {
			
			try(Connection conn = ConnectionUtil.getConnection()){
				
				String sql = "UPDATE ers_reimbursement SET reimb_resolved = CURRENT_TIMESTAMP, reimb_status_id = ?, reimb_resolver = ?"
						+ "WHERE reimb_id = ?;";
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setInt(1, 3);
				statement.setInt(2, user.getUserId());
				statement.setInt(3, reimbId);
				statement.execute();

				return true;
			}catch(SQLException e){
				e.printStackTrace();
			}
			return false;
		}

		@Override
		public User findById(int userId) {
			
			try(Connection conn = ConnectionUtil.getConnection()){
				
				String sql = "SELECT * FROM ers_users WHERE ers_users_id = ?;";
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setInt(1, userId);
				ResultSet result = statement.executeQuery();
				
				User user = new User();
				while(result.next()) {
					user.setUserId(result.getInt("ers_users_id"));
					user.setFirstName(result.getString("user_first_name"));
					user.setLastName(result.getString("user_last_name"));
					user.setEmail(result.getString("user_email"));
					user.setRoleId(result.getInt("user_role_id"));
					
				}
				return user;
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		public User findByUserName(String userName) {
			
			try(Connection conn = ConnectionUtil.getConnection()){
				
				String sql = "SELECT * FROM ers_users WHERE ers_username = ?;";
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setString(1, userName);
				ResultSet result = statement.executeQuery();
				User user = new User();
				
				if(result.next()) {
					user.setUsername(result.getString("ers_username"));
					user.setPassword(result.getString("ers_password"));
					user.setUserId(result.getInt("ers_users_id"));
					user.setFirstName(result.getString("user_first_name"));
					user.setLastName(result.getString("user_last_name"));
					user.setEmail(result.getString("user_email"));
					user.setRoleId(result.getInt("user_role_id"));
					
					return user;
				}else {
					System.out.println("Invalid Username. Please try again.");
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		public User login(String username, String password) {
			
				try(Connection conn = ConnectionUtil.getConnection()){
				
				String sql = "SELECT * FROM ers_users WHERE ers_username = ?";
				PreparedStatement statement = conn.prepareStatement(sql);
				
				statement.setString(1, username);
				ResultSet result = statement.executeQuery();
								
				User user = new User();
				while(result.next()) {
					user.setUserId(result.getInt("ers_users_id"));
					user.setFirstName(result.getString("user_first_name"));
					user.setLastName(result.getString("user_last_name"));
					user.setEmail(result.getString("user_email"));
					user.setRoleId(result.getInt("user_role_id"));
					
					return user;
				}
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return null;
		}

		
	
}