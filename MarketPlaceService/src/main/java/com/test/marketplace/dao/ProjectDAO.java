package com.test.marketplace.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ProjectDAO {

	private int id;
	private String title;
	private String description;
	private BigDecimal maxBidAmout;
	private Date bidLastDate;
	private UserDAO createdBy;
	private List<ProjectBidDAO> bidsList;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public BigDecimal getMaxBidAmout() {
		return maxBidAmout;
	}
	public void setMaxBidAmout(BigDecimal maxBidAmout) {
		this.maxBidAmout = maxBidAmout;
	}
	
	public Date getBidLastDate() {
		return bidLastDate;
	}
	public void setBidLastDate(Date bidLastDate) {
		this.bidLastDate = bidLastDate;
	}
	public UserDAO getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(UserDAO createdBy) {
		this.createdBy = createdBy;
	}
	public List<ProjectBidDAO> getBidsList() {
		return bidsList;
	}
	public void setBidsList(List<ProjectBidDAO> bidsList) {
		this.bidsList = bidsList;
	}
}
