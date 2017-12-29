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
	static App a1 = new App("app1", "joaorsantos", "Gamify UI", "Marketing", "Lorem Ipsum"); // To remove when add MongoDB
	static App a2 = new App("app2", "rcosta", "randomp", "Entertainment", "Lorem Ipsum 2");
	static AchievementManager am = null;
	
	public static AchievementManager getInstance() {
		if(am == null) {
			am = new AchievementManager();
			
			Achievement ach1 = new Achievement("1",a1,"Vitorias","static","10","Game","ganhar 10 jogos");
			
			achievements.add(ach1);
			
		}
		return am;
	}
	
	

	// Create Achievement
	
	public void createAchievement(String idAchivement, App idApp, String name, String structure, String goal, String type,String description) {
		Achievement a = new Achievement(idAchivement,idApp,name,structure,goal,type,description);
		achievements.add(a);		
	}
	
	// Get All Achievements
	
	public List<Achievement> getAchievements(String userID) {
		String checkuser = null;
		boolean permission;

		if (userID.equals(userAuth)) {
			permission = true;
		}
		else {
			permission = false;
		}

		if (permission == true) { 
			List<Achievement> filteredAchievements = new ArrayList<Achievement>();
			for(Achievement achievement:achievements) {
				
				// List only achievements from that user on all achievements available
				
				for(App app:apps) {
					if(achievement.getAppID().toString().equals(app.getAppID())){
						checkuser =app.getUserID();
					}
				
				if (checkuser.equals(userAuth) ) {
					filteredAchievements.add(achievement);
				}
				}
			}
			return filteredAchievements;
		}
		else if (permission == false) {
			// The user is not authorized to see apps from another user - TO DO: Send error	
		}
		return null;
	}
	// Get specific achievement

	public Achievement getAchievement(String userID,String achievementID) {
		String checkuser = null;
		boolean exists=false;
		// permission user belonging achievement
		boolean permission;
		if (userID.equals(userAuth)) {
			permission = true;
		}
		else {
			permission = false;
		}
		
		if (permission == true) { 
			for(Achievement achievement:achievements) {
				for(App app:apps) {
					if(achievement.getAppID().toString().equals(app.getAppID())){
						checkuser =app.getUserID();
					}
				
				if (checkuser.equals(userAuth) ) {
					exists=true;
					return achievement;
					
				}
				}
				}
			
			if (exists == false) {
				// There are no achievement with that ID - TO DO: Send error
			}
		}
		else if (permission == false) {
			// The user is not authorized to see achievements from another user - TO DO: Send error	
		}
		return null;
	}
	
	// Change achievement
	public void changeAchievement(String achievementID,String achievementName,String goal, String type, String description ) {
		boolean exists = false;
		String checkuser = null;

		for(Achievement achievement:achievements) {
			// Check if achievement exists
			if (achievement.getAchievementID().equals(achievementID)) {
				exists = true;
				
			}
			// Check if the user have permission to change the achievement
				for(App app:apps) {
					if(achievement.getAppID().toString().equals(app.getAppID())){
						checkuser =app.getUserID();
					}
				}
				
				
				if (checkuser.equals(userAuth) ) {
					Achievement newAchievement = new Achievement(achievementID,achievement.getAppID(),achievementName,achievement.getStructure(),goal, type, description);
					int i = achievements.indexOf(achievement);
					achievements.set(i, newAchievement);
				}
				else {
					// The user is not authorized to change the achievements from another user - TO DO: Send error	
				}
			
		}
		
		if (exists == false) {
			// There are no achievement with that ID - TO DO: Send error
		}
	}
	
	
	//Remove achievement


	public void removeAchievement(String achievementID) {
		boolean exists = false;
		String checkuser = null;
		
		for(Achievement achievement:achievements) {
			// Check if achievement exists
			if (achievement.getAchievementID().equals(achievementID)) {
				exists = true;
				// Check if the user have permission to delete the achievement
				for(App app:apps) {
					if(achievement.getAppID().toString().equals(app.getAppID())){
						checkuser =app.getUserID();
					}
				}
				
				if (checkuser.equals(userAuth) ) {
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
	


}




	
