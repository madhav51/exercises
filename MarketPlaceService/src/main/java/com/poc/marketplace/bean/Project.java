package com.test.marketplace.bean;

import java.util.List;

public class Project {

	private int id;
	private String title;
	private String description;
	private String maxBidAmout;
	private String maxbidDate;
	private int userId;
	private List<ProjectBid> bidsList;
	
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

	public String getMaxBidAmout() {
		return maxBidAmout;
	}

	public void setMaxBidAmout(String maxBidAmout) {
		this.maxBidAmout = maxBidAmout;
	}

	public List<ProjectBid> getBidsList() {
		return bidsList;
	}

	public void setBidsList(List<ProjectBid> bidsList) {
		this.bidsList = bidsList;
	}

	public String getMaxbidDate() {
		return maxbidDate;
	}

	public void setMaxbidDate(String maxbidDate) {
		this.maxbidDate = maxbidDate;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
