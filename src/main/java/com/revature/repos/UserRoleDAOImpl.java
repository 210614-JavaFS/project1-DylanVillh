package com.revature.repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.UserRole;
import com.revature.utils.ConnectionUtil;

public class UserRoleDAOImpl implements UserRoleDAO{

	@Override
	public UserRole findById(int userRoleId) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "SELECT * FROM ers_user_roles WHERE ers_user_role_id = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, userRoleId);
			ResultSet result = statement.executeQuery();
			UserRole userRole = new UserRole();
			
			while(result.next()) {
				userRole.setRoleId(result.getInt("ers_user_role_id"));
				userRole.setRole(result.getString("user_role"));
			}
			return userRole;
			
		}catch(SQLException e) {
				e.printStackTrace();
			}
		return null;
	}
}