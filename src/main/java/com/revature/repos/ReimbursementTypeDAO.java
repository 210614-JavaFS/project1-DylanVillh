package com.revature.repos;

import com.revature.models.ReimbursementType;

public interface ReimbursementTypeDAO {
	
	public ReimbursementType findByTypeId(int reimTypeId);

}
