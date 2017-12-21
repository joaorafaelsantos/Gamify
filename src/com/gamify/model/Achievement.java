package com.gamify.model;

public class Achievement {

	private String achievementID;
	private App appID;
	private String name;
	private String structure;
	private String goal;
	private String type;
	private String description;
	private String[] inputs;
	
	
	
	
	public Achievement(String achievementID, App appID, String name, String structure, String goal, String type,
			String description) {
		super();
		this.achievementID = achievementID;
		this.appID = appID;
		this.name = name;
		this.structure = structure;
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
	public String getStructure() {
		return structure;
	}
	public void setStructure(String structure) {
		this.structure = structure;
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
	public String[] getInputs() {
		return inputs;
	}
	public void setInputs(String[] inputs) {
		this.inputs = inputs;
	}
}

