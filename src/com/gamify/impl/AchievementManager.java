package com.gamify.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.gamify.interf.InterfaceAchievement;
import com.gamify.model.Achievement;
import com.gamify.model.App;
import com.gamify.model.Input;
import com.gamify.model.User;

public class AchievementManager implements InterfaceAchievement {

	String userAuth = "joaorsantos"; // To do - Change "joaorsantos" by the user who is logged

	static List<Achievement> achievements = new ArrayList<Achievement>();

	// Delete when connect to MongoDB
	static List<App> apps = new ArrayList<App>();

	static AchievementManager am = null;

	public static AchievementManager getInstance() {
		if (am == null) {
			am = new AchievementManager();
			
			// Delete when connect to MongoDB
			App a1 = new App("app1", "joaorsantos", "Gamify UI", "Marketing", "Lorem Ipsum"); // To remove when add																			// MongoDB
			App a2 = new App("app2", "rcosta", "randomp", "Entertainment", "Lorem Ipsum 2");
			apps.add(a1);
			apps.add(a2);

			Achievement ach1 = new Achievement("ach1", "app1", "watch 100", "progressive","Premium account for 1 month", "100", "entertainment", "watch 100 videos");
			Achievement ach2 = new Achievement("ach2", "app2", "Get customer", "static", "1 day off","1", "productivity", "get your fist customer");
			Achievement ach3 = new Achievement("ach3", "app2", "Empates", "static", "1","1 euro", "Game", "empatar 1 jogos");

			achievements.add(ach1);
			achievements.add(ach2);
			achievements.add(ach3);

		}
		return am;
	}

	// Create Achievement
	@Override
	public void createAchievement(String achievementID, String appID, String name, String structure, String reward,
			String goal, String type, String description) {
		Achievement a = new Achievement(achievementID, appID, name, structure, reward,
				 goal, type, description);
		achievements.add(a);
	}
	

	// Get All Achievements
	@Override
	public List<Achievement> getAchievements(String appID) {

		boolean permission = false;

		// Permissions for request

		for (App app : apps) {
			if (app.getAppID().equals(appID)) {

				if (app.getUserID().equals(userAuth)) {
					permission = true;
				}

			}
		}

		if (permission == true) {
			List<Achievement> filteredAchievements = new ArrayList<Achievement>();
			for (Achievement achievement : achievements) {

				// List only achievements from that app on all achievements available

				if (achievement.getAppID().equals(appID)) {
					filteredAchievements.add(achievement);
				}
			}
			return filteredAchievements;

		}

		else if (permission == false) {
			// The user is not authorized to see achievements from another user - TO DO:
			// Send error
		}
		return null;
	}
	// Get specific achievement
	@Override
	public Achievement getAchievement(String appID, String achievementID) {

		boolean exists = false;
		boolean permission = false;

		// Permissions for request

		for (App app : apps) {
			if (app.getAppID().equals(appID)) {

				if (app.getUserID().equals(userAuth)) {
					permission = true;
				}
			} 
		}

		if (permission == true) {
			for (Achievement achievement : achievements) {

				if (achievement.getAchievementID().equals(achievementID)) {
					exists = true;
					return achievement;
				}
			}

			if (exists == false) {
				// There are no achievement with that ID - TO DO: Send error
			}
		} else if (permission == false) {
			// The user is not authorized to see achievements from another user - TO DO:
			// Send error
		}
		return null;
	}

	// Change achievement
	@Override
	public void changeAchievement(String achievementID, String achievementName,String reward, String goal, String type,
			String description) {
		boolean exists = false;
		String checkUser = null;
		Achievement tempAchievement = null;
		int tempAchievementPosition = 0;

		for (Achievement achievement : achievements) {
			// Check if achievement exists
			if (achievement.getAchievementID().equals(achievementID)) {
				exists = true;
				tempAchievement = achievement;
				tempAchievementPosition = achievements.indexOf(achievement);
			}
		}
		
		if (exists == true) {
			// Check if the user have permission to change the achievement
			for (App app : apps) {
				if (tempAchievement.getAppID().equals(app.getAppID())) {
					checkUser = app.getUserID();
				}
			}

			if (checkUser.equals(userAuth)) {
				Achievement newAchievement = new Achievement(achievementID, tempAchievement.getAppID(), achievementName,
						tempAchievement.getStructure(), reward, goal, type, description);
				int i = tempAchievementPosition;
				achievements.set(i, newAchievement);
			} else {
				// The user is not authorized to change the achievements from another user - TO
				// DO: Send error
			}
		}
		else if (exists == false) {
			// There are no achievement with that ID - TO DO: Send error
		}
	}

	// Remove achievement
	@Override
	public void removeAchievement(String achievementID) {
		boolean exists = false;
		String checkUser = null;
		Achievement tempAchievement = null;
		int tempAchievementPosition = 0;

		for (Achievement achievement : achievements) {
			// Check if achievement exists
			if (achievement.getAchievementID().equals(achievementID)) {
				exists = true;
				tempAchievement = achievement;
				tempAchievementPosition = achievements.indexOf(achievement);
			}
		}
		
		if (exists == true) {
			// Check if the user have permission to change the achievement
			for (App app : apps) {
				if (tempAchievement.getAppID().equals(app.getAppID())) {
					checkUser = app.getUserID();
				}
			}

			if (checkUser.equals(userAuth)) {
				achievements.remove(tempAchievement);
			} else {
				// The user is not authorized to change the achievements from another user - TO
				// DO: Send error
			}
		}
		else if (exists == false) {
			// There are no achievement with that ID - TO DO: Send error
		}
	}
	
	
	// Submit inputs 
	@Override
	public Achievement inputsAchievements(String appID,String achievementID, String name, String score) {

		boolean permission = false;
		boolean exists=false;
		Input[] oldinputs=null;
		
		

		// Permissions for request

		for (App app : apps) {
			if (app.getAppID().toString().equals(appID)) {

				
				if (app.getUserID().equals(userAuth)) {
					permission = true;
				}

			} else {
				permission = false;
			}

		}

		if (permission == true) {
			for (Achievement achievement : achievements) {
				if (achievement.getAchievementID().equals(achievementID)) {
					exists = true;
					
					
					for(int i =0; i<=achievement.getInputs().length; i++ ) {
						if (i==achievement.getInputs().length) {
							
							Input input = new Input(name,score);
							
							
							//oldinputs.add(input);
							
						}
					}
					
					
					
					return achievement;
				}
				
			}
			
			

		}

		else if (permission == false) {
			// The user is not authorized to see achievements from another user - TO DO:
			// Send error
		}
		if (exists == false) {
			// There are no achievement with that ID - TO DO: Send error
		}
		return null;
		

		
	}

}
