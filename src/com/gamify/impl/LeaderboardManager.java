package com.gamify.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import com.gamify.data.LeaderboardData;
import com.gamify.data.AppData;
import com.gamify.data.ErrorData;
import com.gamify.data.LeaderboardData;
import com.gamify.interf.InterfaceLeaderboard;
import com.gamify.model.Leaderboard;
import com.gamify.model.App;
import com.gamify.model.Error;
import com.gamify.model.Input;
import com.gamify.model.Leaderboard;

public class LeaderboardManager implements InterfaceLeaderboard {

	static LeaderboardManager lm = null;

	public static LeaderboardManager getInstance() {
		if (lm == null) {
			lm = new LeaderboardManager();
		}
		return lm;
	}

	// Create Leaderboard
	@Override
	public Response createLeaderboard(String appID, String name, String type, String description, String userAuth) {

		AppData appData = AppData.getInstance();
		List<App> apps = appData.getAllData();

		for (App app : apps) {
			if (app.getUserID().equals(userAuth)) {
				LeaderboardData leaderboardData = LeaderboardData.getInstance();
				List<Leaderboard> leaderboards = leaderboardData.getData(appID);

				boolean exists = false;
				for (int i = 0; i < leaderboards.size(); i++) {
					if (leaderboards.get(i).getName().equals(name)) {
						exists = true;
						break;
					}
				}

				if (exists) {
					ErrorData errorData = ErrorData.getInstance();
					Error error = errorData.getData("14");
					return Response.serverError().status(Integer.parseInt(error.getHttp_status())).type("text/plain")
							.entity(error.getMessage()).build();
				} else {
					String leaderboardID = "";
					Input input = new Input("", "");
					List<Input> inputs = new ArrayList<Input>();
					inputs.add(input);
					if (leaderboards.size() > 0) {
						int newID = Integer.parseInt(
								leaderboards.get(leaderboards.size() - 1).getLeaderboardID().replace("lb", "")) + 1;
						leaderboardID = "lb" + Integer.toString(newID);
					} else {
						int newID = 1;
						leaderboardID = "lb" + Integer.toString(newID);
					}

					Leaderboard leaderboard = new Leaderboard(leaderboardID, appID, name, type, description, inputs);
					leaderboardData.insertData(leaderboard);
					// The leaderboard is created with success
					return Response.ok().entity("Leaderboard " + name + " created!").build();
				}

			} else {
				// The user is not authorized to create leaderboards from another user
				ErrorData errorData = ErrorData.getInstance();
				errorData.getData("3");
			}
		}
		return null;

	}

	// Get All Leaderboards

	public List<Leaderboard> getLeaderboards(String appID, String userAuth) {
		boolean exists = false;

		AppData appData = AppData.getInstance();
		List<App> apps = appData.getAllData();

		LeaderboardData leaderboardData = LeaderboardData.getInstance();

		// Permissions for request

		for (App app : apps) {
			if (app.getAppID().equals(appID)) {

				if (app.getUserID().equals(userAuth)) {

					return leaderboardData.getData(appID);
				} else {
					// The user is not authorized to see leaderboards from another user
					ErrorData errorData = ErrorData.getInstance();
					errorData.getData("3");
				}

			}
		}
		return null;
	}

	// Get specific leaderboard
	@Override
	public Leaderboard getLeaderboard(String appID, String leaderboardID, String userAuth) {
		boolean exists = false;

		AppData appData = AppData.getInstance();
		List<App> apps = appData.getAllData();

		LeaderboardData leaderboardData = LeaderboardData.getInstance();

		// Permissions for request

		for (App app : apps) {
			if (app.getAppID().equals(appID)) {

				if (app.getUserID().equals(userAuth)) {

					return leaderboardData.getSpecificData(appID, leaderboardID);
				} else {
					// The user is not authorized to see leaderboards from another user
					ErrorData errorData = ErrorData.getInstance();
					errorData.getData("3");
				}

			}
		}
		return null;
	}

	// Change leaderboard
	@Override
	public void changeLeaderboard(String appID, String leaderboardID, String name, String type, String description,
			String userAuth) {
		boolean appExists = false;
		boolean permissions = true;
		AppData appData = AppData.getInstance();
		List<App> apps = appData.getAllData();

		LeaderboardData leaderboardData = LeaderboardData.getInstance();

		for (App app : apps) {
			// Check if app exists
			if (app.getAppID().equals(appID) && app.getUserID().equals(userAuth)) {
				appExists = true;
				permissions = true;
				break;
			}
		}

		if (!appExists) {
			// App doesn't exist
			ErrorData errorData = ErrorData.getInstance();
			errorData.getData("7");
		} else if (appExists && permissions) {
			Object leaderboard = leaderboardData.getSpecificData(appID, leaderboardID);

			if (leaderboard == null) {
				// Leaderboard doesn't exist
				ErrorData errorData = ErrorData.getInstance();
				errorData.getData("9");
			} else {
				leaderboardData.changeData(appID, leaderboardID, name, type, description);
			}

		} else if (!permissions) {
			// The user is not authorized to change leaderboards from another user
			ErrorData errorData = ErrorData.getInstance();
			errorData.getData("3");
		}
	}

	// Remove Leaderboard
	@Override
	public void removeLeaderboard(String appID, String leaderboardID, String userAuth) {
		boolean appExists = false;
		boolean permissions = true;
		AppData appData = AppData.getInstance();
		List<App> apps = appData.getAllData();

		LeaderboardData leaderboardData = LeaderboardData.getInstance();

		for (App app : apps) {
			// Check if app exists
			if (app.getAppID().equals(appID) && app.getUserID().equals(userAuth)) {
				appExists = true;
				permissions = true;
				break;
			}
		}

		if (!appExists) {
			// App doesn't exist
			ErrorData errorData = ErrorData.getInstance();
			errorData.getData("7");
		} else if (appExists && permissions) {
			Object leaderboard = leaderboardData.getSpecificData(appID, leaderboardID);

			if (leaderboard == null) {
				// Leaderboard doesn't exist
				ErrorData errorData = ErrorData.getInstance();
				errorData.getData("9");
			} else {
				leaderboardData.removeData(appID, leaderboardID);
			}
		} else if (!permissions) {
			// The user is not authorized to change leaderboards from another user
			ErrorData errorData = ErrorData.getInstance();
			errorData.getData("3");
		}
	}

	// Submit inputs

	public void addInputs(String appID, String leaderboardID, String name, String score, String userAuth) {

		boolean permission = false;
		boolean exists = false;
		boolean added = false;

		AppData appData = AppData.getInstance();
		List<App> apps = appData.getAllData();

		LeaderboardData leaderboardData = LeaderboardData.getInstance();

		// Permissions for request

		for (App app : apps) {
			if (app.getAppID().equals(appID)) {

				if (app.getUserID().equals(userAuth)) {
					permission = true;
				}
			}
		}

		if (permission == true) {

			Leaderboard leaderboard = leaderboardData.getSpecificData(appID, leaderboardID);

			if (leaderboard != null) {
				exists = true;

				List<Input> inputs = new ArrayList<Input>(leaderboard.getInputs());

				for (Input input : inputs) {
					if (input.getName().equals(name)) {
						input.setScore(score);
						added = true;
					}
				}

				if (!added) {
					Input input = new Input(name, score);
					if (input.getName().equals("") && input.getScore().equals("")) {
						inputs.clear();
					}
					inputs.add(input);
				}

				leaderboard.setInputs(inputs);
				leaderboardData.inputData(appID, leaderboardID, leaderboard);

			}

			if (exists == false) {
				// Leaderboard doesn't exist
				ErrorData errorData = ErrorData.getInstance();
				errorData.getData("9");
			} else if (permission == false) {
				// The user is not authorized to add inputs to leaderboards from another user
				ErrorData errorData = ErrorData.getInstance();
				errorData.getData("3");
			}
		}

	}

	// RESETS

	public void resetLeaderboardScore(String appID, String leaderboardID, String userAuth) {

		boolean permission = false;
		boolean exists = false;

		AppData appData = AppData.getInstance();
		List<App> apps = appData.getAllData();

		LeaderboardData leaderboardData = LeaderboardData.getInstance();

		// Permissions for request

		for (App app : apps) {
			if (app.getAppID().equals(appID)) {

				if (app.getUserID().equals(userAuth)) {
					permission = true;
				}
			}
		}

		if (permission == true) {

			Leaderboard leaderboard = leaderboardData.getSpecificData(appID, leaderboardID);

			if (leaderboard != null) {
				exists = true;

				List<Input> inputs = new ArrayList<Input>(leaderboard.getInputs());

				for (Input input : inputs) {
					input.setScore("0");
				}
				leaderboard.setInputs(inputs);
				leaderboardData.inputData(appID, leaderboardID, leaderboard);

			}

			if (exists == false) {
				// Leaderboard doesn't exist
				ErrorData errorData = ErrorData.getInstance();
				errorData.getData("9");
			} else if (permission == false) {
				// The user is not authorized to add inputs to leaderboards from another user
				ErrorData errorData = ErrorData.getInstance();
				errorData.getData("3");
			}
		}

	}

	public void resetLeaderboardTotal(String appID, String leaderboardID, String userAuth) {

		boolean permission = false;
		boolean exists = false;

		AppData appData = AppData.getInstance();
		List<App> apps = appData.getAllData();

		LeaderboardData leaderboardData = LeaderboardData.getInstance();

		// Permissions for request

		for (App app : apps) {
			if (app.getAppID().equals(appID)) {

				if (app.getUserID().equals(userAuth)) {
					permission = true;
				}
			}
		}

		if (permission == true) {

			Leaderboard leaderboard = leaderboardData.getSpecificData(appID, leaderboardID);

			if (leaderboard != null) {
				exists = true;

				List<Input> inputs = new ArrayList<Input>();
				Input input = new Input("", "");
				inputs.add(input);

				leaderboard.setInputs(inputs);
				leaderboardData.inputData(appID, leaderboardID, leaderboard);

			}

			if (exists == false) {
				// Leaderboard doesn't exist
				ErrorData errorData = ErrorData.getInstance();
				errorData.getData("9");
			} else if (permission == false) {
				// The user is not authorized to add inputs to leaderboards from another user
				ErrorData errorData = ErrorData.getInstance();
				errorData.getData("3");
			}
		}

	}
}
