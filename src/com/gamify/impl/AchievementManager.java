package com.gamify.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import com.gamify.data.AchievementData;
import com.gamify.data.AppData;
import com.gamify.data.ErrorData;
import com.gamify.interf.InterfaceAchievement;
import com.gamify.model.Achievement;
import com.gamify.model.App;
import com.gamify.model.Error;
import com.gamify.model.Input;

public class AchievementManager implements InterfaceAchievement {

	static AchievementManager am = null;

	public static AchievementManager getInstance() {
		if (am == null) {
			am = new AchievementManager();
		}
		return am;
	}

	// Create Achievement
	@Override
	public Response createAchievement(String appID, String name, String structure, String reward, String goal,
			String type, String description, String userAuth) {

		AppData appData = AppData.getInstance();
		List<App> apps = appData.getAllData();

		for (App app : apps) {
			if (app.getUserID().equals(userAuth)) {
				AchievementData achievementData = AchievementData.getInstance();
				List<Achievement> achievements = achievementData.getData(appID);

				boolean exists = false;
				for (int i = 0; i < achievements.size(); i++) {
					if (achievements.get(i).getName().equals(name)) {
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
					Input input = new Input("", "");
					List<Input> inputs = new ArrayList<Input>();
					inputs.add(input);
					int newID = Integer.parseInt(
							achievements.get(achievements.size() - 1).getAchievementID().replace("ach", "")) + 1;
					String achievementID = "ach" + Integer.toString(newID);
					Achievement achievement = new Achievement(achievementID, appID, name, structure, reward, goal, type,
							description, inputs);
					achievementData.insertData(achievement);
					// The achievement is created with success
					return Response.ok().entity("Achievement " + name + " created!").build();
				}

			} else {
				// The user is not authorized to create achievements from another user
				ErrorData errorData = ErrorData.getInstance();
				errorData.getData("3");
			}
		}
		return null;

	}

	// Get All Achievements
	@Override
	public Object getAchievements(String appID, String userAuth) {

		boolean exists = false;

		AppData appData = AppData.getInstance();
		List<App> apps = appData.getAllData();

		AchievementData achievementData = AchievementData.getInstance();

		// Permissions for request

		for (App app : apps) {
			if (app.getAppID().equals(appID)) {

				if (app.getUserID().equals(userAuth)) {
					return achievementData.getData(appID);
				} else {
					// The user is not authorized to see achievements from another user
					ErrorData errorData = ErrorData.getInstance();
					return errorData.getData("3");
				}

			}
		}
		return null;
	}

	// Get specific achievement
	@Override
	public Object getAchievement(String appID, String achievementID, String userAuth) {

		boolean exists = false;

		AppData appData = AppData.getInstance();
		List<App> apps = appData.getAllData();

		AchievementData achievementData = AchievementData.getInstance();

		// Permissions for request

		for (App app : apps) {
			if (app.getAppID().equals(appID)) {

				if (app.getUserID().equals(userAuth)) {

					return achievementData.getSpecificData(appID, achievementID);
				} else {
					// The user is not authorized to see achievements from another user
					ErrorData errorData = ErrorData.getInstance();
					errorData.getData("3");
				}

			}
		}
		return null;

	}

	// Change achievement
	@Override
	public void changeAchievement(String appID, String achievementID, String name, String reward, String goal,
			String type, String description, String userAuth) {

		boolean appExists = false;
		boolean permissions = true;
		AppData appData = AppData.getInstance();
		List<App> apps = appData.getAllData();

		AchievementData achievementData = AchievementData.getInstance();

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
			Object achievement = achievementData.getSpecificData(appID, achievementID);

			if (achievement == null) {
				// Achievement doesn't exist
				ErrorData errorData = ErrorData.getInstance();
				errorData.getData("11");
			} else {
				achievementData.changeData(appID, achievementID, name, reward, goal, type, description);
			}
		} else if (!permissions) {
			// The user is not authorized to change achievements from another user
			ErrorData errorData = ErrorData.getInstance();
			errorData.getData("3");
		}

	}

	// Remove achievement
	@Override
	public void removeAchievement(String appID, String achievementID, String userAuth) {
		boolean appExists = false;
		boolean permissions = true;
		AppData appData = AppData.getInstance();
		List<App> apps = appData.getAllData();

		AchievementData achievementData = AchievementData.getInstance();

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
			Object achievement = achievementData.getSpecificData(appID, achievementID);

			if (achievement == null) {
				// Achievement doesn't exist
				ErrorData errorData = ErrorData.getInstance();
				errorData.getData("11");
			} else {
				achievementData.removeData(appID, achievementID);
			}
		} else if (!permissions) {
			// The user is not authorized to change achievements from another user
			ErrorData errorData = ErrorData.getInstance();
			errorData.getData("3");
		}
	}

	// Submit inputs
	@Override
	public void addInputs(String appID, String achievementID, String name, String score, String userAuth) {

		boolean permission = false;
		boolean exists = false;
		boolean added = false;

		AppData appData = AppData.getInstance();
		List<App> apps = appData.getAllData();

		AchievementData achievementData = AchievementData.getInstance();

		// Permissions for request

		for (App app : apps) {
			if (app.getAppID().equals(appID)) {

				if (app.getUserID().equals(userAuth)) {
					permission = true;
				}
			}
		}

		if (permission == true) {

			Achievement achievement = achievementData.getSpecificData(appID, achievementID);

			if (achievement != null) {
				exists = true;

				List<Input> inputs = new ArrayList<Input>(achievement.getInputs());

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

				achievement.setInputs(inputs);
				achievementData.inputData(appID, achievementID, achievement);

			}

			if (exists == false) {
				// Achievement doesn't exist
				ErrorData errorData = ErrorData.getInstance();
				errorData.getData("11");
			} else if (permission == false) {
				// The user is not authorized to add inputs to achievements from another user
				ErrorData errorData = ErrorData.getInstance();
				errorData.getData("3");
			}
		}

	}
}
