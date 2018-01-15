package com.test.marketplace.dao;

public class UserDAO {

	private int id;
	private int userId;
	private String type;
	
	public UserDAO(int id, int userId, String type) {
		this.id = id;
		this.userId = userId;
		this.type = type;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
