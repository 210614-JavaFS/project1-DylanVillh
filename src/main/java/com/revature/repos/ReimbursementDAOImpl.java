package com.revature.repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.utils.ConnectionUtil;

public class ReimbursementDAOImpl implements ReimbursementDAO {

	UserDAO userDao = new UserDAOImpl();
	ReimbursementStatusDAO reimbursementStatusDAO = new ReimbursementStatusDAOImpl();
	ReimbursementTypeDAO reimbursementTypeDAO = new ReimbursementTypeDAOImpl();
	
	@Override
	public List<Reimbursement> findAllReimbursement() {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "SELECT * FROM ers_reimbursement;";

			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			List<Reimbursement> list = new ArrayList<>();
			
			while(result.next()) {
				Reimbursement reimbursement = new Reimbursement();
				reimbursement.setReimbId(result.getInt("reimb_id"));
				reimbursement.setReimbAmount(result.getDouble("reimb_amount"));
				reimbursement.setReimbSubmitted(result.getString("reimb_submitted"));
				reimbursement.setReimbResolved(result.getString("reimb_resolved"));
				reimbursement.setReimbDescription(result.getString("reimb_description"));
				reimbursement.setReimbAuthorId(result.getInt("reimb_author"));
				reimbursement.setReimbResolverId(result.getInt("reimb_resolver"));
				reimbursement.setReimbStatusId(result.getInt("reimb_status_id"));
				reimbursement.setReimbTypeId(result.getInt("reimb_type_id"));
				list.add(reimbursement);
			}
			return list;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public List<Reimbursement> findAllPendingReimbursement() {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "SELECT * FROM ers_reimbursement WHERE reimb_status_id = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, 1);
			ResultSet result = statement.executeQuery();
			List<Reimbursement> list = new ArrayList<>();
			
			while(result.next()) {
				Reimbursement reimbursement = new Reimbursement();
				reimbursement.setReimbId(result.getInt("reimb_id"));
				reimbursement.setReimbAmount(result.getDouble("reimb_amount"));
				reimbursement.setReimbSubmitted(result.getString("reimb_submitted"));
				reimbursement.setReimbResolved(result.getString("reimb_resolved"));
				reimbursement.setReimbDescription(result.getString("reimb_description"));
				reimbursement.setReimbAuthorId(result.getInt("reimb_author"));
				reimbursement.setReimbResolverId(result.getInt("reimb_resolver"));
				reimbursement.setReimbStatusId(result.getInt("reimb_status_id"));
				reimbursement.setReimbTypeId(result.getInt("reimb_type_id"));
				list.add(reimbursement);
			}
			
			return list;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	@Override
	public List<Reimbursement> findAllReimbursementByAuthor(int userId) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "SELECT * FROM ers_reimbursement WHERE reimb_author = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, userId);
			
			ResultSet result = statement.executeQuery();
			List<Reimbursement> list = new ArrayList<>();
			
			while(result.next()) {
				Reimbursement reimbursement = new Reimbursement();
				reimbursement.setReimbId(result.getInt("reimb_id"));
				reimbursement.setReimbAmount(result.getDouble("reimb_amount"));
				reimbursement.setReimbSubmitted(result.getString("reimb_submitted"));
				reimbursement.setReimbResolved(result.getString("reimb_resolved"));
				reimbursement.setReimbDescription(result.getString("reimb_description"));
				reimbursement.setReimbStatusId(result.getInt("reimb_status_id"));
				reimbursement.setReimbTypeId(result.getInt("reimb_type_id"));
				list.add(reimbursement);
			}

			
			return list;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	@Override
	public Reimbursement findReimbursementById(int reimbId) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "SELECT * FROM ers_reimbursement WHERE reimb_id = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, reimbId);
			ResultSet result = statement.executeQuery();
			
			Reimbursement reimbursement = new Reimbursement();
			while(result.next()) {
				reimbursement.setReimbId(result.getInt("reimb_id"));
				reimbursement.setReimbAmount(result.getDouble("reimb_amount"));
				reimbursement.setReimbSubmitted(result.getString("reimb_submitted"));
				reimbursement.setReimbResolved(result.getString("reimb_resolved"));
				reimbursement.setReimbDescription(result.getString("reimb_description"));
				reimbursement.setReimbAuthorId(result.getInt("reimb_author"));
				reimbursement.setReimbResolverId(result.getInt("reimb_resolver"));
				reimbursement.setReimbStatusId(result.getInt("reimb_status_id"));
				reimbursement.setReimbTypeId(result.getInt("reimb_type_id"));
			}

			return reimbursement;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	@Override
	public boolean addReimbursement(Reimbursement reimbursement) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "INSERT INTO ers_reimbursement "
					+ "(reimb_amount, reimb_resolved, reimb_description, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id)"
					+ "VALUES (?,?,?,?,?,?,?);";
			PreparedStatement statement = conn.prepareStatement(sql);

			int index = 0;;
			statement.setDouble(++index, reimbursement.getReimbAmount());
			statement.setTimestamp(++index, null);
			statement.setString(++index, reimbursement.getReimbDescription());
			statement.setInt(++index, reimbursement.getReimbAuthorId());
			statement.setInt(++index,0);
			statement.setInt(++index, 1);
			statement.setInt(++index, reimbursement.getReimbTypeId());
			statement.execute();
			
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
