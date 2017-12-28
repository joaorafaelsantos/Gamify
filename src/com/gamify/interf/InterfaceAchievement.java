package com.gamify.interf;

import java.util.List;

import com.gamify.model.Achievement;
import com.gamify.model.App;

public interface InterfaceAchievement {

	public Achievement getAchievement(String achievementID);
	public List<Achievement> getAchievements(String userRequested);
	public List<Achievement> getAchievements();
	public void createAchievement(String achievementID, App appID, String name, String structure, String goal, String type,String description);
	public void removeAchievement(String achievementID);
	public void changeAchievement(String achievementID, Achievement oldAchievement, Achievement newAchievement);

}


