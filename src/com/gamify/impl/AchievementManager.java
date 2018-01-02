package com.gamify.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.gamify.interf.InterfaceAchievement;
import com.gamify.model.Achievement;
import com.gamify.model.App;
import com.gamify.model.User;

public class AchievementManager implements InterfaceAchievement {

	String userAuth = "joaorsantos"; // To do - Change "joaorsantos" by the user who is logged

	static List<Achievement> achievements = new ArrayList<Achievement>();

	// Delete when connect to MongoDB
	static List<App> apps = new ArrayList<App>();
	static App a1 = new App("app1", "joaorsantos", "Gamify UI", "Marketing", "Lorem Ipsum"); // To remove when add																			// MongoDB
	static App a2 = new App("app2", "rcosta", "randomp", "Entertainment", "Lorem Ipsum 2");
	static AchievementManager am = null;

	public static AchievementManager getInstance() {
		if (am == null) {
			am = new AchievementManager();

			Achievement ach1 = new Achievement("ach1", a1, "watch 100", "progressive","Premium account for 1 month", "100", "entertainment", "watch 100 videos");
			Achievement ach2 = new Achievement("ach2", a2, "Get customer", "static", "1 day off","1", "productivity", "get your fist customer");
			Achievement ach3 = new Achievement("ach3", a2, "Empates", "static", "1","1 euro", "Game", "empatar 1 jogos");

			achievements.add(ach1);
			achievements.add(ach2);
			achievements.add(ach3);

		}
		return am;
	}

	// Create Achievement

	public void createAchievement(String idAchivement, App idApp, String name, String structure, String reward, String goal,
			String type, String description) {
		Achievement a = new Achievement(idAchivement, idApp, name, structure,reward, goal, type, description);
		achievements.add(a);
	}

	// Get All Achievements

	public List<Achievement> getAchievements(String appID) {

		boolean permission = false;

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
			List<Achievement> filteredAchievements = new ArrayList<Achievement>();
			for (Achievement achievement : achievements) {

				// List only achievements from that app on all achievements available

				if (achievement.getAppID().toString().equals(appID)) {
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

	public Achievement getAchievement(String appID, String achievementID) {

		boolean exists = false;
		boolean permission = false;

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

				if (achievement.getAppID().toString().equals(appID)) {
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
	public void changeAchievement(String achievementID, String achievementName,String reward, String goal, String type,
			String description) {
		boolean exists = false;
		String checkuser = null;

		for (Achievement achievement : achievements) {
			// Check if achievement exists
			if (achievement.getAchievementID().equals(achievementID)) {
				exists = true;

			}
			// Check if the user have permission to change the achievement
			for (App app : apps) {
				if (achievement.getAppID().toString().equals(app.getAppID())) {
					checkuser = app.getUserID();
				}
			}

			if (checkuser.equals(userAuth)) {
				Achievement newAchievement = new Achievement(achievementID, achievement.getAppID(), achievementName,
						achievement.getStructure(), reward, goal, type, description);
				int i = achievements.indexOf(achievement);
				achievements.set(i, newAchievement);
			} else {
				// The user is not authorized to change the achievements from another user - TO
				// DO: Send error
			}

		}

		if (exists == false) {
			// There are no achievement with that ID - TO DO: Send error
		}
	}

	// Remove achievement

	public void removeAchievement(String achievementID) {
		boolean exists = false;
		String checkuser = null;

		for (Achievement achievement : achievements) {
			// Check if achievement exists
			if (achievement.getAchievementID().equals(achievementID)) {
				exists = true;
				// Check if the user have permission to delete the achievement
				for (App app : apps) {
					if (achievement.getAppID().toString().equals(app.getAppID())) {
						checkuser = app.getUserID();
					}
				}

				if (checkuser.equals(userAuth)) {
					achievements.remove(achievement);
				}

				else {
					// The user is not authorized to remove - TO DO: Send error
				}
			}
		}

		if (exists == false) {
			// There are no achievement with that ID - TO DO: Send error
		}
	}
	
	
	// Submit inputs 

	public Achievement inputsAchievements(String appID,String achievementID, String name, String score) {

		boolean permission = false;
		boolean exists=false;
		String[] oldInputs;
		

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
					oldInputs =achievement.getInputs();
					
					//return achievement;
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
