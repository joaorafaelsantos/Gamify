package com.gamify.model;

public class Achievement {

	private String idAchivement;
	private App idApp;
	private String name;
	private String structure;
	private String goal;
	private String type;
	private String description;
	private String[] inputs;
	
	
	
	
	public Achievement(String idAchivement, App idApp, String name, String structure, String goal, String type,
			String description) {
		super();
		this.idAchivement = idAchivement;
		this.idApp = idApp;
		this.name = name;
		this.structure = structure;
		this.goal = goal;
		this.type = type;
		this.description = description;
		this.inputs=null;
	}
	
	public String getIdAchivement() {
		return idAchivement;
	}
	public void setIdAchivement(String idAchivement) {
		this.idAchivement = idAchivement;
	}
	public App getIdApp() {
		return idApp;
	}
	public void setIdApp(App idApp) {
		this.idApp = idApp;
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

