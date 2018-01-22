package com.gamify.model;


import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "achievement")
public class Achievement {

	private String achievementID;
	private String appID;
	private String name;
	private String structure;
	private String reward;
	private String goal;
	private String type;
	private String description;
	private List<Input> inputs = new ArrayList<Input>();
	
	public Achievement() {
	}
	
	public Achievement(String achievementID, String appID, String name, String structure, String reward, String goal,
			String type, String description, List<Input> inputs) {
		super();
		this.achievementID = achievementID;
		this.appID = appID;
		this.name = name;
		this.structure = structure;
		this.reward = reward;
		this.goal = goal;
		this.type = type;
		this.description = description;
		this.inputs = inputs;
	}
	
	@XmlElement(name = "achievementID")
	public String getAchievementID() {
		return achievementID;
	}
	public void setAchievementID(String achievementID) {
		this.achievementID = achievementID;
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
	
	@XmlElement(name = "structure")
	public String getStructure() {
		return structure;
	}
	public void setStructure(String structure) {
		this.structure = structure;
	}
	
	@XmlElement(name = "reward")
	public String getReward() {
		return reward;
	}
	public void setReward(String reward) {
		this.reward = reward;
	}
	
	@XmlElement(name = "goal")
	public String getGoal() {
		return goal;
	}
	public void setGoal(String goal) {
		this.goal = goal;
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

