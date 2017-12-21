package com.gamify.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.gamify.interf.InterfaceAchievement;
import com.gamify.model.Achievement;
import com.gamify.model.App;

public class AchievementManager implements InterfaceAchievement {

	static List<Achievement> achievements = new ArrayList<Achievement>();


	// Encher o ArrayList
	//  static AchievementManager am = null;
	//	
	//	public static AchievementManager getInstance() {
	//		if(am == null) {
	//			am = new GameManager();
	//			Game g1 = new Game("Fifa", "EASPORTS", 2017, "Sport");
	//			games.add(g1);
	//		}
	//		return am;
	//	}

	public Achievement getAchievement(String achievementID) {

		for (Iterator<Achievement> iterator = achievements.iterator(); iterator.hasNext();) {
			Achievement achievement = (Achievement) iterator.next();
			if(achievement.getAchievementID().equals(achievementID))
				return achievement;
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
			if(achievement.getAchievementID().equals(achievementID))
				iterator.remove();
		}
	}

}


//
//public class GameManager implements iGame{
//
//	static List<Game> games = new ArrayList<Game>();
//	
//	
//	static GameManager gm = null;
//	
//	public static GameManager getInstance() {
//		if(gm == null) {
//			gm = new GameManager();
//			
//			Game g1 = new Game("Fifa", "EASPORTS", 2017, "Sport");
//			Game g2 = new Game("Lemmings", "XPTO", 1983, "Platforms");
//			Game g3 = new Game("Doom", "XPTO", 1999, "FPS");
//			Game g4 = new Game("Quake", "XPTO", 2003, "FPS");
//			games.add(g1);
//			games.add(g2);
//			games.add(g3);
//			games.add(g4);
//		}
//		return gm;
//	}
//
//	
//
//
//	public List<Game> getGames(String category) {
//		List<Game> filteredGames = new ArrayList<Game>();
//		for (Iterator<Game> iterator = games.iterator(); iterator.hasNext();) {
//			Game game = (Game) iterator.next();
//			if(game.getCategory().equals(category))
//				filteredGames.add(game);
//		}		
//		return filteredGames;
//	}
//	
//	
//
//
//}
