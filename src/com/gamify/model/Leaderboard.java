package com.gamify.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "leaderboard")
public class Leaderboard {

	private String leaderboardID;
	private String appID;
	private String name;
	private String type;
	private String description;
	private List<Input> inputs = new ArrayList<Input>();

	public Leaderboard() {
	}

	public Leaderboard(String leaderboardID, String appID, String name, String type, String description,
			List<Input> inputs) {
		super();
		this.leaderboardID = leaderboardID;
		this.appID = appID;
		this.name = name;
		this.type = type;
		this.description = description;
		this.inputs = inputs;
	}

	@XmlElement(name = "leaderboardID")
	public String getLeaderboardID() {
		return leaderboardID;
	}

	public void setLeaderboardID(String leaderboardID) {
		this.leaderboardID = leaderboardID;
	}

	@XmlElement(name = "appID")
	public String getAppID() {
		return appID;
	}

	public void setAppID(String appID) {
		this.appID = appID;
	}

	@XmlElement(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	@XmlElement(name = "inputs")
	public List<Input> getInputs() {
		return inputs;
	}

	public void setInputs(List<Input> inputs) {
		this.inputs = inputs;
	}

}
