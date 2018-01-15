package com.gamify.interf;

public interface InterfaceAchievement {

	public void createAchievement(String achievementID, String appID, String name, String structure, String reward, String goal, String type, String description);
	public Object getAchievements(String appID);
	public Object getAchievement(String userID,String achievementID);
	public void changeAchievement(String appID, String achievementID,String achievementName,String reward, String goal, String type, String description );
	public void removeAchievement(String appID, String achievementID);
	public void addInputs(String appID,String achievementID, String name, String score);
	

}


