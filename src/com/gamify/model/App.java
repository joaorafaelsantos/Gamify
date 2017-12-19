package com.gamify.model;

public class App {
	
	private String idApp;
	private User username;
	private String appName;
	private String type;
	private String description;
	
	public App(String idApp, User username, String appName, String type, String description) {
		super();
		this.idApp = idApp;
		this.username = username;
		this.appName = appName;
		this.type = type;
		this.description = description;
	}

	public String getIdApp() {
		return idApp;
	}

	public void setIdApp(String idApp) {
		this.idApp = idApp;
	}

	public User getUsername() {
		return username;
	}

	public void setUsername(User username) {
		this.username = username;
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
