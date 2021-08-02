package com.revature.services;

import java.util.List;

import com.revature.repos.ReimbursementDAO;
import com.revature.repos.ReimbursementDAOImpl;
import com.revature.models.Reimbursement;

public class ReimbursementService {

private static ReimbursementDAO reimbursementDAO = new ReimbursementDAOImpl();
	
	public List<Reimbursement> getAllReimbursement() {
		return reimbursementDAO.findAllReimbursement();
	}
	
	
	public List<Reimbursement> getPendingReimbursement() {
		return reimbursementDAO.findAllPendingReimbursement();
	}
	
	
	public List<Reimbursement> getReimbursementByAuthor(int userId){
		return reimbursementDAO.findAllReimbursementByAuthor(userId);
			
	}
	
	
	public Reimbursement getReimbursementById(int reimbId) {
		return reimbursementDAO.findReimbursementById(reimbId);
	}
	
	public boolean addReimbursement(Reimbursement reimbursement) {
		return reimbursementDAO.addReimbursement(reimbursement);
	}
	
}