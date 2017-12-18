package com.gamify.impl;

public class AchievementManager {

	public AchievementManager() {
		// TODO Auto-generated constructor stub
	}

}

//package com.gamify.impl;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//import com.gamify.model.Game;
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
//	public List<Game> getGames() {
//		return games;
//	}
//
//	public void createGame(String title, String company, int year, String category) {
//		Game g = new Game(title, company, year, category);
//		games.add(g);		
//	}
//
//	public Game getGame(String title) {
//		for (Iterator<Game> iterator = games.iterator(); iterator.hasNext();) {
//			Game game = (Game) iterator.next();
//			if(game.getTitle().equals(title))
//				return game;
//		}
//		return null;
//	}
//
//	public void removeGame(String title) {
//		for (Iterator<Game> iterator = games.iterator(); iterator.hasNext();) {
//			Game game = (Game) iterator.next();
//			if(game.getTitle().equals(title))
//				iterator.remove();
//		}
//	}
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
