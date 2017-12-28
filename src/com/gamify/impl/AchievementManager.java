package com.gamify.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.gamify.interf.InterfaceAchievement;
import com.gamify.model.Achievement;
import com.gamify.model.App;
import com.gamify.model.User;

public class AchievementManager implements InterfaceAchievement {
	
	
	String userLogged = "joaorsantos"; // To do - Change "joaorsantos" by the user who is logged
	static List<Achievement> achievements = new ArrayList<Achievement>();

	// Delete when connect to MongoDB
	
	static User u1 = new User("joaorsantos", "xpto", "joaorsantos@gamify.pt"); // To remove when add MongoDB
	static App a1 = new App("app1", u1, "Gamify UI", "Marketing", "Lorem Ipsum"); // To remove when add MongoDB
	static AchievementManager am = null;
	
	public static AchievementManager getInstance() {
		if(am == null) {
			am = new AchievementManager();
			
			Achievement ach1 = new Achievement("1",a1,"Vitorias","static","10","Game","ganhar 10 jogos");
			
			achievements.add(ach1);
			
		}
		return am;
	}

	

	public Achievement getAchievement(String achievementID) {
		
		
		// permission user belonging achievement
		boolean permission=true;
		if(permission) {
			
			for (Iterator<Achievement> iterator = achievements.iterator(); iterator.hasNext();) {
				Achievement achievement = (Achievement) iterator.next();
				if(achievement.getAchievementID().equals(achievementID))
					{
					return achievement;
					}else {
						//send errors
					}
			} 
			
		}

		

		return null;
	}
	
	public List<Achievement> getAchievements(String userRequested) {

		if (userRequested.equals(userLogged)) { 
			List<Achievement> filteredAchievements = new ArrayList<Achievement>();
			for(Achievement achievement:achievements) {
				// search for user on list, get their apps
				
				// App appUser;
				// for user apps {
				//	if (achievement.getAppID() == appUser) {
				//	filteredAchievements.add(achievement);
				//		}
				//}
				
			}
			return filteredAchievements;
		}
		else {
			// The user is not authorized to see achievements from another user - TO DO: Send error	
		}
		return null;
	}


	public void createAchievement(String idAchivement, App idApp, String name, String structure, String goal, String type,String description) {
		Achievement a = new Achievement(idAchivement,idApp,name,structure,goal,type,description);
		achievements.add(a);		
	}

	public List<Achievement> getAchievements() {
		return achievements;
	}
	


	public void removeAchievement(String achievementID) {
		for (Iterator<Achievement> iterator = achievements.iterator(); iterator.hasNext();) {
			Achievement achievement = (Achievement) iterator.next();
			if(achievement.getAchievementID().equals(achievementID)) {
				iterator.remove();
			}else {
					//send errors
			}
				
		}
	}
	
	public void changeAchievement(String achievementID, Achievement oldAchievement, Achievement newAchievement) {
		
	}

}




	
