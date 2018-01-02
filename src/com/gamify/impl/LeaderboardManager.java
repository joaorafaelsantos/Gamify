package com.gamify.impl;

import java.util.ArrayList;
import java.util.List;

import com.gamify.interf.InterfaceAchievement;
import com.gamify.interf.InterfaceLeaderboard;
import com.gamify.model.Achievement;
import com.gamify.model.App;
import com.gamify.model.Leaderboard;

public class LeaderboardManager implements InterfaceLeaderboard {
	
	
	String userAuth = "joaorsantos"; // To do - Change "joaorsantos" by the user who is logged
	
	static List<Leaderboard> leaderboards = new ArrayList<Leaderboard>();

	// Delete when connect to MongoDB
	static List<App> apps = new ArrayList<App>();
	static App a1 = new App("app1", "joaorsantos", "Gamify UI", "Marketing", "Lorem Ipsum"); // To remove when add																			// MongoDB
	static App a2 = new App("app2", "rcosta", "randomp", "Entertainment", "Lorem Ipsum 2");
	
	
	static LeaderboardManager lm = null;
	public static LeaderboardManager getInstance() {
		if (lm == null) {
			lm = new LeaderboardManager();

			Leaderboard lb1 = new Leaderboard("lb1", a1, "Most Active Users", "entertainment","most active users ranking");
			Leaderboard lb2 = new Leaderboard("lb2", a2, "Most Comments", "education","useres with more comments ranking");
			

			leaderboards.add(lb1);
			leaderboards.add(lb2);
			
		}
		return lm;
	}
	
	// Create Leaderboard

		public void createLeaderboard(String idLeaderboard, App idApp, String name,String type, String description) {
			Leaderboard l = new Leaderboard(idLeaderboard, idApp, name,type, description);
			leaderboards.add(l);
		}

	// Get All Leaderboards

		public List<Leaderboard> getleaderboards(String appID) {

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
				List<Leaderboard> filteredLeaderboards = new ArrayList<Leaderboard>();
				for (Leaderboard leaderboard : leaderboards) {

					// List only Leaderboards from that app on all Leaderboards available

					if (leaderboard.getAppID().toString().equals(appID)) {
						filteredLeaderboards.add(leaderboard);

					}
				}
				return filteredLeaderboards;

			}

			else if (permission == false) {
				// The user is not authorized to see achievements from another user - TO DO:
				// Send error
			}
			return null;
		}
}
