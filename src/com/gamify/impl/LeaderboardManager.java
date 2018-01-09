package com.gamify.impl;

import java.util.ArrayList;
import java.util.List;

import com.gamify.interf.InterfaceAchievement;
import com.gamify.interf.InterfaceLeaderboard;
import com.gamify.model.Achievement;
import com.gamify.model.App;
import com.gamify.model.Input;
import com.gamify.model.Leaderboard;

public class LeaderboardManager implements InterfaceLeaderboard {

	String userAuth = "joaorsantos"; // To do - Change "joaorsantos" by the user who is logged

	static List<Leaderboard> leaderboards = new ArrayList<Leaderboard>();

	// Delete when connect to MongoDB
	static List<App> apps = new ArrayList<App>();
	static App a1 = new App("app1", "joaorsantos", "Gamify UI", "Marketing", "Lorem Ipsum"); // To remove when add //
																								// MongoDB
	static App a2 = new App("app2", "rcosta", "randomp", "Entertainment", "Lorem Ipsum 2");

	static LeaderboardManager lm = null;

	public static LeaderboardManager getInstance() {
		if (lm == null) {
			lm = new LeaderboardManager();

			Leaderboard lb1 = new Leaderboard("lb1", a1, "Most Active Users", "entertainment",
					"most active users ranking");
			Leaderboard lb2 = new Leaderboard("lb2", a2, "Most Comments", "education",
					"useres with more comments ranking");

			leaderboards.add(lb1);
			leaderboards.add(lb2);

		}
		return lm;
	}

	// Create Leaderboard

	public void createLeaderboard(String leaderboardID, App idApp, String name, String type, String description) {
		Leaderboard l = new Leaderboard(leaderboardID, idApp, name, type, description);
		leaderboards.add(l);
	}

	// Get All Leaderboards

	public List<Leaderboard> getLeaderboards(String appID) {

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
			// The user is not authorized to see leaderboard from another user - TO DO:
			// Send error
		}
		return null;
	}

	// Get specific leaderboard

	public Leaderboard getLeaderboard(String appID, String leaderboardID) {

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

			for (Leaderboard leaderboard : leaderboards) {

				if (leaderboard.getAppID().toString().equals(appID)) {
					exists = true;
					return leaderboard;

				}
			}

			if (exists == false) {
				// There are no leaderboard with that ID - TO DO: Send error
			}
		} else if (permission == false) {
			// The user is not authorized to see leaderboards from another user - TO DO:
			// Send error
		}
		return null;
	}

	// Change leaderboard

	public void changeLeaderboard(String leaderboardID, String leaderboardName, String type, String description) {
		boolean exists = false;
		String checkuser = null;

		for (Leaderboard leaderboard : leaderboards) {
			// Check if leaderboard exists
			if (leaderboard.getLeaderboardID().equals(leaderboardID)) {
				exists = true;

			}
			// Check if the user have permission to change the leaderboard
			for (App app : apps) {
				if (leaderboard.getAppID().toString().equals(app.getAppID())) {
					checkuser = app.getUserID();
				}
			}

			if (checkuser.equals(userAuth)) {
				Leaderboard newLeaderboard = new Leaderboard(leaderboardID, leaderboard.getAppID(), leaderboardName,
						type, description);
				int i = leaderboards.indexOf(leaderboard);
				leaderboards.set(i, newLeaderboard);
			} else {
				// The user is not authorized to change the leaderboards from another user - TO
				// DO: Send error
			}

		}

		if (exists == false) {
			// There are no leaderboard with that ID - TO DO: Send error
		}
	}

	// Remove Leaderboard

	public void removeLeaderboard(String leaderboardID) {
		boolean exists = false;
		String checkuser = null;

		for (Leaderboard leaderboard : leaderboards) {
			// Check if leaderboard exists
			if (leaderboard.getLeaderboardID().equals(leaderboardID)) {
				exists = true;
				// Check if the user have permission to delete the leaderboard
				for (App app : apps) {
					if (leaderboard.getAppID().toString().equals(app.getAppID())) {
						checkuser = app.getUserID();
					}
				}

				if (checkuser.equals(userAuth)) {
					leaderboards.remove(leaderboard);
				}

				else {
					// The user is not authorized to remove - TO DO: Send error
				}
			}
		}

		if (exists == false) {
			// There are no leaderboard with that ID - TO DO: Send error
		}
	}

	// Submit inputs

	public Leaderboard inputsLeaderboards(String appID, String leaderboardID, String name, String score) {

		boolean permission = false;
		boolean exists = false;
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

			// TO:DO
			//
			//
			//

		}

		else if (permission == false) {
			// The user is not authorized to change leaderboard from another user - TO DO:
			// Send error
		}
		if (exists == false) {
			// There are no leaderboard with that ID - TO DO: Send error
		}
		return null;

	}

	// RESETS

	public void resetLeaderBoardScore(String appID, String leaderboardID) {

		boolean permission = false;
		boolean exists = false;

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

			// TO:DO
			//
			//
			//

		}

		else if (permission == false) {
			// The user is not authorized to reset leaderboard from another user - TO DO:
			// Send error
		}
		if (exists == false) {
			// There are no leaderboard with that ID - TO DO: Send error
		}

	}

	public void resetLeaderBoardTotal(String appID, String leaderboardID) {

		boolean permission = false;
		boolean exists = false;

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

			for (Leaderboard leaderboard : leaderboards) {
				if (leaderboard.getLeaderboardID().equals(leaderboardID)) {
					exists = true;
					
					leaderboard.setInputs(null);
		
					
				}
				
			}

		}

		else if (permission == false) {
			// The user is not authorized to reset leaderboard from another user - TO DO:
			// Send error
		}
		if (exists == false) {
			// There are no leaderboard with that ID - TO DO: Send error
		}
	}

}
