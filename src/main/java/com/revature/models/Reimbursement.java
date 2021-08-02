package com.revature.models;

import java.sql.Timestamp;

public class Reimbursement {
	
	private int reimbId;
	private double reimbAmount;
	private Timestamp reimbSubmitted;
	private Timestamp reimbResolved = null;
	private String reimbDescription = null;
	private int reimbAuthorId;
	private int reimbResolverId;
	private int reimbStatusId;
	private int reimbTypeId;
	
	public Reimbursement(int reimbId, double reimbAmount, Timestamp reimbSubmitted, Timestamp reimbResolved,
			String reimbDescription, int reimbAuthorId, int reimbResolverId, int reimbStatusId, int reimbTypeId) {
		super();
		this.reimbId = reimbId;
		this.reimbAmount = reimbAmount;
		this.reimbSubmitted = reimbSubmitted;
		this.reimbResolved = reimbResolved;
		this.reimbDescription = reimbDescription;
		this.reimbAuthorId = reimbAuthorId;
		this.reimbResolverId = reimbResolverId;
		this.reimbStatusId = reimbStatusId;
		this.reimbTypeId = reimbTypeId;
	}

	public Reimbursement() {
		super();
	}

	public int getReimbId() {
		return reimbId;
	}

	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}

	public double getReimbAmount() {
		return reimbAmount;
	}

	public void setReimbAmount(double reimbAmount) {
		this.reimbAmount = reimbAmount;
	}

	public Timestamp getReimbSubmitted() {
		return reimbSubmitted;
	}

	public void setReimbSubmitted(Timestamp timestamp) {
		this.reimbSubmitted = timestamp;
	}

	public Timestamp getReimbResolved() {
		return reimbResolved;
	}

	public void setReimbResolved(Timestamp timestamp) {
		this.reimbResolved = timestamp;
	}

	public String getReimbDescription() {
		return reimbDescription;
	}

	public void setReimbDescription(String reimbDescription) {
		this.reimbDescription = reimbDescription;
	}

	public int getReimbAuthorId() {
		return reimbAuthorId;
	}

	public void setReimbAuthorId(int reimbAuthorId) {
		this.reimbAuthorId = reimbAuthorId;
	}

	public int getReimbResolverId() {
		return reimbResolverId;
	}

	public void setReimbResolverId(int reimbResolverId) {
		this.reimbResolverId = reimbResolverId;
	}

	public int getReimbStatusId() {
		return reimbStatusId;
	}

	public void setReimbStatusId(int reimbStatusId) {
		this.reimbStatusId = reimbStatusId;
	}

	public int getReimbTypeId() {
		return reimbTypeId;
	}

	public void setReimbTypeId(int reimbTypeId) {
		this.reimbTypeId = reimbTypeId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(reimbAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + reimbAuthorId;
		result = prime * result + ((reimbDescription == null) ? 0 : reimbDescription.hashCode());
		result = prime * result + reimbId;
		result = prime * result + ((reimbResolved == null) ? 0 : reimbResolved.hashCode());
		result = prime * result + reimbResolverId;
		result = prime * result + reimbStatusId;
		result = prime * result + ((reimbSubmitted == null) ? 0 : reimbSubmitted.hashCode());
		result = prime * result + reimbTypeId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (Double.doubleToLongBits(reimbAmount) != Double.doubleToLongBits(other.reimbAmount))
			return false;
		if (reimbAuthorId != other.reimbAuthorId)
			return false;
		if (reimbDescription == null) {
			if (other.reimbDescription != null)
				return false;
		} else if (!reimbDescription.equals(other.reimbDescription))
			return false;
		if (reimbId != other.reimbId)
			return false;
		if (reimbResolved == null) {
			if (other.reimbResolved != null)
				return false;
		} else if (!reimbResolved.equals(other.reimbResolved))
			return false;
		if (reimbResolverId != other.reimbResolverId)
			return false;
		if (reimbStatusId != other.reimbStatusId)
			return false;
		if (reimbSubmitted == null) {
			if (other.reimbSubmitted != null)
				return false;
		} else if (!reimbSubmitted.equals(other.reimbSubmitted))
			return false;
		if (reimbTypeId != other.reimbTypeId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reimbursement [reimbId=" + reimbId + ", reimbAmount=" + reimbAmount + ", reimbSubmitted="
				+ reimbSubmitted + ", reimbResolved=" + reimbResolved + ", reimbDescription=" + reimbDescription
				+ ", reimbAuthorId=" + reimbAuthorId + ", reimbResolverId=" + reimbResolverId + ", reimbStatusId="
				+ reimbStatusId + ", reimbTypeId=" + reimbTypeId + "]";
	}
	
	
	

}
