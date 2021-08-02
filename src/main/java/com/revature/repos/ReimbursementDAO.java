package com.revature.repos;

import java.util.List;

import com.revature.models.Reimbursement;

public interface ReimbursementDAO {
	
	public List<Reimbursement> findAllReimbursement();
	
	public List<Reimbursement> findAllPendingReimbursement();
	
	public List<Reimbursement> findAllReimbursementByAuthor(int userId);
	
	public Reimbursement findReimbursementById(int reimbId);
	
	public boolean addReimbursement(Reimbursement reimbursement);

}
