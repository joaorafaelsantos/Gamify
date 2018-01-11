package com.gamify.model;


import java.util.List;

public class Achievement {

	private String achievementID;
	private String appID;
	private String name;
	private String structure;
	private String reward;
	private String goal;
	private String type;
	private String description;
	private List<Input> inputs;
	
	public Achievement() {}
	
	public Achievement(String achievementID, String appID, String name, String structure, String reward, String goal,
			String type, String description) {
		super();
		this.achievementID = achievementID;
		this.appID = appID;
		this.name = name;
		this.structure = structure;
		this.reward = reward;
		this.goal = goal;
		this.type = type;
		this.description = description;
		this.inputs=null;
	}
	public String getAchievementID() {
		return achievementID;
	}
	public void setAchievementID(String achievementID) {
		this.achievementID = achievementID;
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
	public String getStructure() {
		return structure;
	}
	public void setStructure(String structure) {
		this.structure = structure;
	}
	public String getReward() {
		return reward;
	}
	public void setReward(String reward) {
		this.reward = reward;
	}
	public String getGoal() {
		return goal;
	}
	public void setGoal(String goal) {
		this.goal = goal;
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
	public void setInputs(List<Input> inputs) {
		this.inputs = inputs;
	}
	
	
	
}

