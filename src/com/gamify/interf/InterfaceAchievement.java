package com.gamify.interf;

public interface InterfaceAchievement {

	public Object createAchievement (String appID, String name, String structure, String reward,
			String goal, String type, String description, String userAuth);

	public Object getAchievements(String appID, String userAuth);

	public Object getAchievement(String userID, String achievementID, String userAuth);

	public void changeAchievement(String appID, String achievementID, String achievementName, String reward,
			String goal, String type, String description, String userAuth);

	public void removeAchievement(String appID, String achievementID, String userAuth);

	public void addInputs(String appID, String achievementID, String name, String score, String userAuth);

}
