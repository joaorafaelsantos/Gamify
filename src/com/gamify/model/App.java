package com.gamify.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "app")
public class App {

	private String appID;
	private String userID;
	private String appName;
	private String type;
	private String description;

	public App() {
	}

	public App(String appID, String userID, String appName, String type, String description) {
		super();
		this.appID = appID;
		this.userID = userID;
		this.appName = appName;
		this.type = type;
		this.description = description;
	}

	@XmlElement(name = "appID")
	public String getAppID() {
		return appID;
	}

	public void setAppID(String appID) {
		this.appID = appID;
	}

	@XmlElement(name = "userID")
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	@XmlElement(name = "appName")
	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	@XmlElement(name = "type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@XmlElement(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
