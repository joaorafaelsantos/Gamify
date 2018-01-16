package com.gamify.interf;

import java.util.List;


import com.gamify.model.App;
import com.gamify.model.Leaderboard;

public interface InterfaceLeaderboard {
	
	public void createLeaderboard(String leaderboardID, String appID, String name, String type, String description, String userAuth);
	public Object getLeaderboards(String appID, String userAuth);
	public Object getLeaderboard(String appID, String leaderboardID, String userAuth);
	public void changeLeaderboard(String appID, String leaderboardID, String leaderboardName, String type, String description, String userAuth);
	public void removeLeaderboard(String appID, String leaderboardID, String userAuth);
	public void addInputs(String appID, String leaderboardID, String name, String score, String userAuth);
	public void resetLeaderboardScore(String appID, String leaderboardID, String userAuth);
	public void resetLeaderboardTotal(String appID, String leaderboardID, String userAuth);

}
