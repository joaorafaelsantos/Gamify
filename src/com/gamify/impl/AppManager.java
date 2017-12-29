package com.gamify.impl;

import java.util.ArrayList;
import java.util.List;
import com.gamify.interf.InterfaceApp;
import com.gamify.model.App;

public class AppManager implements InterfaceApp {

	String userAuth = "joaorsantos"; // To change when add auth (token)

	static List<App> apps = new ArrayList<App>();

	static AppManager am = null;

	public static AppManager getInstance() {
		if(am == null) {
			am = new AppManager();
			App a1 = new App("app1", "joaorsantos", "Gamify UI", "Marketing", "Lorem Ipsum"); // To remove when add MongoDB
			App a2 = new App("app1", "rcosta", "randomp", "Entertainment", "Lorem Ipsum 2"); // To remove when add MongoDB
			apps.add(a1); // To remove when add MongoDB
			apps.add(a2); // To remove when add MongoDB
		}
		return am;
	}

	// Create new app

	@Override
	public void createApp(String appID, String userID, String appName, String type, String description) {
		App a = new App(appID, userID, appName, type, description);
		apps.add(a);
	}

	// Get all apps

	@Override
	public List<App> getApps(String userRequested) {

		boolean permission;

		if (userRequested.equals(userAuth)) {
			permission = true;
		}
		else {
			permission = false;
		}

		if (permission == true) { 
			List<App> filteredApps = new ArrayList<App>();
			for(App app:apps) {
				// List only apps from that user on all apps available
				if (app.getUserID().equals(userRequested)) {
					filteredApps.add(app);
				}
			}
			return filteredApps;
		}
		else if (permission == false) {
			// The user is not authorized to see apps from another user - TO DO: Send error	
		}
		return null;

	}

	// Get specific app

	@Override
	public App getApp(String userID, String appID) {
		boolean permission;
		boolean exists = false;

		if (userID.equals(userAuth)) {
			permission = true;
		}
		else {
			permission = false;
		}

		if (permission == true) { 
			for(App app:apps) {
				if(app.getAppID().equals(appID)) {
					exists = true;
					return app;
				}
			}
			if (exists == false) {
				// There are no app with that ID - TO DO: Send error
			}
		}
		else if (permission == false) {
			// The user is not authorized to see apps from another user - TO DO: Send error	
		}
		return null;
	}

	// Change app

	@Override
	public void changeApp(String appID, String appName, String type, String description) {

		boolean exists = false;

		for(App app:apps) {
			// Check if app exists
			if (app.getAppID().equals(appID)) {
				exists = true;
				// Check if the user have permission to change the app
				if (app.getUserID().equals(userAuth) ) {
					App newApp = new App(appID, app.getUserID(),appName, type, description);
					int i = apps.indexOf(app);
					apps.set(i, newApp);
				}
				else {
					// The user is not authorized to change the apps from another user - TO DO: Send error	
				}
			}
		}
		
		if (exists == false) {
			// There are no app with that ID - TO DO: Send error
		}
	}

	// Remove app

	@Override
	public void removeApp(String appID) {
		
		boolean exists = false;
		
		for(App app:apps) {
			// Check if app exists
			if (app.getAppID().equals(appID)) {
				exists = true;
				// Check if the user have permission to change the app
				if (app.getUserID().equals(userAuth)) { 
					apps.remove(app);
				}
				else {
					// The user is not authorized to remove - TO DO: Send error
				}
			}
		}
		
		if (exists == false) {
			// There are no app with that ID - TO DO: Send error
		}
	}


}

