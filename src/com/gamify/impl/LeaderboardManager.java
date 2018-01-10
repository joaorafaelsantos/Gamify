package com.gamify.impl;

import java.util.ArrayList;
import java.util.List;


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

	static LeaderboardManager lm = null;

	public static LeaderboardManager getInstance() {
		if (lm == null) {
			lm = new LeaderboardManager();
			App a1 = new App("app1", "joaorsantos", "Gamify UI", "Marketing", "Lorem Ipsum"); 
			App a2 = new App("app2", "rcosta", "randomp", "Entertainment", "Lorem Ipsum 2");
			apps.add(a1);
			apps.add(a2);

			Leaderboard lb1 = new Leaderboard("lb1", "app1", "Most Active Users", "entertainment",
					"most active users ranking");
			Leaderboard lb2 = new Leaderboard("lb2", "app2", "Most Comments", "education",
					"useres with more comments ranking");
			
			List<Input> inputs = new ArrayList<Input>();
			
			lb1.setInputs(inputs);
			
			
			leaderboards.add(lb1);
			leaderboards.add(lb2);

		}
		return lm;
	}

	// Create Leaderboard
	@Override
	public void createLeaderboard(String leaderboardID, String appID, String name, String type, String description) {
		Leaderboard l = new Leaderboard(leaderboardID, appID, name, type, description);
		List<Input> inputs = new ArrayList<Input>();
		l.setInputs(inputs);
		leaderboards.add(l);
	}

	// Get All Leaderboards

	public List<Leaderboard> getLeaderboards(String appID) {

		boolean permission = false;

		// Permissions for request

		for (App app : apps) {
			if (app.getAppID().equals(appID)) {

				if (app.getUserID().equals(userAuth)) {
					permission = true;
				}

			}
		}

		if (permission == true) {
			List<Leaderboard> filteredLeaderboards = new ArrayList<Leaderboard>();
			for (Leaderboard leaderboard : leaderboards) {

				// List only leaderboards from that app on all leaderboards available

				if (leaderboard.getAppID().equals(appID)) {
					filteredLeaderboards.add(leaderboard);
				}
			}
			return filteredLeaderboards;

		}

		else if (permission == false) {
			// The user is not authorized to see leaderboards from another user - TO DO:
			// Send error
		}
		return null;
	}

	// Get specific leaderboard
	@Override
	public Leaderboard getLeaderboard(String appID, String leaderboardID) {

		boolean exists = false;
		boolean permission = false;

		// Permissions for request

		for (App app : apps) {
			if (app.getAppID().equals(appID)) {

				if (app.getUserID().equals(userAuth)) {
					permission = true;
				}
			} 
		}

		if (permission == true) {
			for (Leaderboard leaderboard : leaderboards) {

				if (leaderboard.getLeaderboardID().equals(leaderboardID)) {
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
	@Override
	public void changeLeaderboard(String leaderboardID, String name, String type, String description) {
		
		boolean exists = false;
		String checkUser = null;
		Leaderboard tempLeaderboard = null;
		int tempLeaderboardPosition = 0;

		for (Leaderboard leaderboard : leaderboards) {
			// Check if leaderboard exists
			if (leaderboard.getLeaderboardID().equals(leaderboardID)) {
				exists = true;
				tempLeaderboard = leaderboard;
				tempLeaderboardPosition = leaderboards.indexOf(leaderboard);
			}
		}
		
		if (exists == true) {
			// Check if the user have permission to change the leaderboard
			for (App app : apps) {
				if (tempLeaderboard.getAppID().equals(app.getAppID())) {
					checkUser = app.getUserID();
				}
			}

			if (checkUser.equals(userAuth)) {
				Leaderboard newLeaderboard = new Leaderboard(leaderboardID, tempLeaderboard.getAppID(), name, type, description);
				int i = tempLeaderboardPosition;
				leaderboards.set(i, newLeaderboard);
			} else {
				// The user is not authorized to change the leaderboards from another user - TO
				// DO: Send error
			}
		}
		else if (exists == false) {
			// There are no leaderboard with that ID - TO DO: Send error
		}
	}

	// Remove Leaderboard
	@Override
	public void removeLeaderboard(String leaderboardID) {
		boolean exists = false;
		String checkUser = null;
		Leaderboard tempLeaderboard = null;
		
		for (Leaderboard leaderboard : leaderboards) {
			// Check if leaderboard exists
			if (leaderboard.getLeaderboardID().equals(leaderboardID)) {
				exists = true;
				tempLeaderboard = leaderboard;
				
			}
		}
		
		if (exists == true) {
			// Check if the user have permission to change the leaderboard
			for (App app : apps) {
				if (tempLeaderboard.getAppID().equals(app.getAppID())) {
					checkUser = app.getUserID();
				}
			}

			if (checkUser.equals(userAuth)) {
				leaderboards.remove(tempLeaderboard);
			} else {
				// The user is not authorized to change the leaderboards from another user - TO
				// DO: Send error
			}
		}
		else if (exists == false) {
			// There are no leaderboard with that ID - TO DO: Send error
		}
	}

	// Submit inputs

	public Leaderboard inputsLeaderboards(String appID, String leaderboardID, String name, String score) {

		boolean permission = false;
		boolean exists = false;
		

		// Permissions for request

		for (App app : apps) {
			if (app.getAppID().equals(appID)) {

				if (app.getUserID().equals(userAuth)) {
					permission = true;
				}
			} 
		}

		if (permission == true) {

			for (Leaderboard leaderboard : leaderboards) {

				if (leaderboard.getLeaderboardID().equals(leaderboardID)) {
					exists = true;
					
					
					List<Input> inputs = new ArrayList<Input>(leaderboard.getInputs());
					
					Input input = new Input(name,score);
					inputs.add(input);
					leaderboard.setInputs(inputs);
					
				}
			}

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
					if (app.getAppID().equals(appID)) {

						if (app.getUserID().equals(userAuth)) {
							permission = true;
						}
					} 
				}

		if (permission == true) {

			// TO:DO
			// iterar sobre o arraylist leaderboard.getInputs() score = 0;
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
			if (app.getAppID().equals(appID)) {

				if (app.getUserID().equals(userAuth)) {
					permission = true;
				}
			} 
		}

		if (permission == true) {

			for (Leaderboard leaderboard : leaderboards) {
				if (leaderboard.getLeaderboardID().equals(leaderboardID)) {
					exists = true;

					List<Input> inputs = new ArrayList<Input>();
					
					leaderboard.setInputs(inputs);

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
