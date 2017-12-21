package com.gamify.interf;

import java.util.List;

import com.gamify.model.Achievement;
import com.gamify.model.App;

public interface InterfaceAchievement {

	public Achievement getAchievement(String achievementID);
	public List<Achievement> getAchievements();
	public void createAchievement(String achievementID, App appID, String name, String structure, String goal, String type,String description);
	public void removeAchievement(String achievementID);

}


//
//public interface iGame {
//	
//	public Game getGame(String title);
//	public List<Game> getGames();
//	public List<Game> getGames(String category);
//	public void createGame(String title, String company, int year, String category);
//	public void removeGame(String title);
//}
