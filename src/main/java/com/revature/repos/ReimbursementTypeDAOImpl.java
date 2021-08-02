package com.revature.repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.ReimbursementType;
import com.revature.utils.ConnectionUtil;

public class ReimbursementTypeDAOImpl implements ReimbursementTypeDAO {

	@Override
	public ReimbursementType findByTypeId(int reimbTypeId) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "SELECT * FROM ers_reimbursement_type WHERE reim_type_id = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, reimbTypeId);
			ResultSet result = statement.executeQuery();
			ReimbursementType reimbursementType = new ReimbursementType();
			while(result.next()) {
				reimbursementType.setTypeId(result.getInt("reim_type_id"));
				reimbursementType.setType(result.getString("reim_type"));
			}
			return reimbursementType;
			
		}catch(SQLException e) {
				e.printStackTrace();
			}
		return null;
	}
}