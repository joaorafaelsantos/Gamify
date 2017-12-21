package com.gamify.model;

public class App {

	private String appID;
	private User userID;
	private String appName;
	private String type;
	private String description;

	public App(String appID, User userID, String appName, String type, String description) {
		super();
		this.appID = appID;
		this.userID = userID;
		this.appName = appName;
		this.type = type;
		this.description = description;
	}

	public String getAppID() {
		return appID;
	}

	public void setAppID(String appID) {
		this.appID = appID;
	}

	public String getUserID() {
		return userID.getUserID();
	}

	public void setUserID(User userID) {
		this.userID = userID;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}







}
