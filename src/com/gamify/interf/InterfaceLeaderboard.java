package com.gamify.interf;

import java.util.List;


import com.gamify.model.App;
import com.gamify.model.Leaderboard;

public interface InterfaceLeaderboard {
	
	public void createLeaderboard(String leaderboardID, String appID, String name, String type, String description);
	public Object getLeaderboards(String appID);
	public Object getLeaderboard(String appID, String leaderboardID);
	public void changeLeaderboard(String appID, String leaderboardID, String leaderboardName, String type, String description);
	public void removeLeaderboard(String appID, String leaderboardID);
	public void addInputs(String appID, String leaderboardID, String name, String score);
	public void resetLeaderboardScore(String appID, String leaderboardID);
	public void resetLeaderboardTotal(String appID, String leaderboardID);

}
