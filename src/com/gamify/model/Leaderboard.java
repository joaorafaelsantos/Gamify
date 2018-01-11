package com.gamify.model;

import java.util.List;

public class Leaderboard {

	private String leaderboardID;
	private String appID;
	private String name;
	private String type;
	private String description;
	private List<Input> inputs;
	
	public Leaderboard() {}

	public Leaderboard(String leaderboardID, String appID, String name, String type, String description) {
		super();
		this.leaderboardID = leaderboardID;
		this.appID = appID;
		this.name = name;
		this.type = type;
		this.description = description;
		this.inputs = null;
	}


	public String getLeaderboardID() {
		return leaderboardID;
	}

	public void setLeaderboardID(String leaderboardID) {
		this.leaderboardID = leaderboardID;
	}

	public String getAppID() {
		return appID;
	}

	public void setAppID(String appID) {
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


	public List<Input> getInputs() {
		return inputs;
	}


	public void setInputs(List<Input>inputs) {
		this.inputs = inputs;
	}

	
	
}
