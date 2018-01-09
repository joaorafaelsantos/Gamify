package com.gamify.model;

public class Leaderboard {

	private String leaderboardID;
	private App appID;
	private String name;
	private String type;
	private String description;
	private Input[] input;
	
	
	
	public Leaderboard(String leaderboardID, App appID, String name, String type, String description) {
		super();
		this.leaderboardID = leaderboardID;
		this.appID = appID;
		this.name = name;
		this.type = type;
		this.description = description;
		this.input = null;
	}


	public String getLeaderboardID() {
		return leaderboardID;
	}

	public void setLeaderboardID(String leaderboardID) {
		this.leaderboardID = leaderboardID;
	}

	public App getAppID() {
		return appID;
	}

	public void setAppID(App appID) {
		this.appID = appID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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


	public Input[] getInputs() {
		return input;
	}


	public void setInputs(Input[] inputs) {
		this.input = inputs;
	}

	
	
}
