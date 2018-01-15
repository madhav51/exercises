package com.test.marketplace.dao;

import java.math.BigDecimal;
import java.util.Date;

public class ProjectBidDAO implements Comparable<ProjectBidDAO>{

	private int id;
	private BigDecimal bidAmount;
	private ProjectDAO projectDao;
	private Date submittedDate;
	private UserDAO submittedBy;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public BigDecimal getBidAmount() {
		return bidAmount;
	}
	public void setBidAmount(BigDecimal bidAmount) {
		this.bidAmount = bidAmount;
	}
	
	public ProjectDAO getProjectDao() {
		return projectDao;
	}
	public void setProjectDao(ProjectDAO projectDao) {
		this.projectDao = projectDao;
	}
	public Date getSubmittedDate() {
		return submittedDate;
	}
	public void setSubmittedDate(Date submittedDate) {
		this.submittedDate = submittedDate;
	}
	public UserDAO getSubmittedBy() {
		return submittedBy;
	}
	public void setSubmittedBy(UserDAO submittedBy) {
		this.submittedBy = submittedBy;
	}

	@Override
	public int compareTo(ProjectBidDAO obj) {
		return (this.bidAmount.compareTo(obj.getBidAmount()));
	}

}
