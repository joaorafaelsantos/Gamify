package com.gamify.interf;

import java.util.List;

import com.gamify.model.Achievement;
import com.gamify.model.App;

public interface InterfaceAchievement {

	public void createAchievement(String achievementID, App appID, String name, String structure,String reward, String goal, String type,String description);
	public List<Achievement> getAchievements(String userRequested);
	public Achievement getAchievement(String userID,String achievementID);
	public void changeAchievement(String achievementID,String achievementName,String reward, String goal, String type, String description );
	public void removeAchievement(String achievementID);
	public Achievement inputsAchievements(String appID,String achievementID, String name, String score);
	

}


