package com.gamify.interf;

import java.util.List;


import com.gamify.model.App;
import com.gamify.model.Leaderboard;

public interface InterfaceLeaderboard {
	
	public void createLeaderboard(String leaderboardID, String appID, String name, String type, String description);
	public List<Leaderboard> getLeaderboards(String appID);
	public Leaderboard getLeaderboard(String appID, String leaderboardID);
	public void changeLeaderboard(String leaderboardID, String leaderboardName, String type, String description);
	public void removeLeaderboard(String leaderboardID);
	public Leaderboard inputsLeaderboards(String appID, String leaderboardID, String name, String score);
	public void resetLeaderBoardScore(String appID, String leaderboardID);
	public void resetLeaderBoardTotal(String appID, String leaderboardID);

}
