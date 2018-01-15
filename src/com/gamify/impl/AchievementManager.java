package com.gamify.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.core.Response;

import com.gamify.data.AchievementData;
import com.gamify.data.AppData;
import com.gamify.data.ErrorData;
import com.gamify.data.UserData;
import com.gamify.interf.InterfaceAchievement;
import com.gamify.model.Achievement;
import com.gamify.model.App;
import com.gamify.model.Input;
import com.gamify.model.User;

public class AchievementManager implements InterfaceAchievement {

	String userAuth = "joaorsantos"; // To do - Change "joaorsantos" by the user who is logged

	static AchievementManager am = null;

	public static AchievementManager getInstance() {
		if (am == null) {
			am = new AchievementManager();
		}
		return am;
	}

	// Create Achievement
	@Override
	public void createAchievement(String achievementID, String appID, String name, String structure, String reward,
			String goal, String type, String description) {
		
		Input input = new Input("", "");
		List<Input> inputs = new ArrayList<Input>();
		inputs.add(input);
		Achievement achievement = new Achievement(achievementID, appID, name, structure, reward, goal, type,
				description, inputs);
		AchievementData achievementData = AchievementData.getInstance();
		achievementData.insertData(achievement);

	}

	// Get All Achievements
	@Override
	public Object getAchievements(String appID) {

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
					// The user is not authorized to see achievements from another user - TO DO:
					// Send error
				}

			}
		}
		return null;
	}

	// Get specific achievement
	@Override
	public Object getAchievement(String appID, String achievementID) {

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
					// The user is not authorized to see achievements from another user - TO DO:
					// Send error
				}

			}
		}
		return null;

	}

	// Change achievement
	@Override
	public void changeAchievement(String appID, String achievementID, String name, String reward, String goal,
			String type, String description) {

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
			// SEND APP NOT EXIST - ERROR
		} else if (appExists && permissions) {
			Object achievement = achievementData.getSpecificData(appID, achievementID);

			if (achievement == null) {
				// SEND ACHIEVEMENT NOT EXIST - ERROR
			} else {
				achievementData.changeData(appID, achievementID, name, reward, goal, type, description);
			}
		}

	}

	// Remove achievement
	@Override
	public void removeAchievement(String appID, String achievementID) {
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
			// SEND APP NOT EXIST - ERROR
		} else if (appExists && permissions) {
			Object achievement = achievementData.getSpecificData(appID, achievementID);

			if (achievement == null) {
				// SEND ACHIEVEMENT NOT EXIST - ERROR
			} else {
				achievementData.removeData(appID, achievementID);
			}
		}
	}

	// Submit inputs
	@Override
	public void addInputs(String appID, String achievementID, String name, String score) {

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

			if (achievement == null) {
				// SEND ACHIEVEMENT NOT EXIST - ERROR
			} else {
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
				// There are no achievement with that ID - TO DO: Send error
			} else if (permission == false) {
				// The user is not authorized to see achievements from another user - TO DO:
				// Send error
			}
		}

	}
}
