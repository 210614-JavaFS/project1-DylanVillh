package com.revature.repos;

import com.revature.models.ReimbursementStatus;

public interface ReimbursementStatusDAO {
	
	public ReimbursementStatus findByStatusId(int reimStatusId);

}
