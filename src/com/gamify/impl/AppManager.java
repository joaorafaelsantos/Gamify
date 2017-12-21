package com.gamify.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.gamify.interf.InterfaceApp;
import com.gamify.model.App;
import com.gamify.model.User;

public class AppManager implements InterfaceApp {

	String userLogged = "joaorsantos"; // To do - Change "joaorsantos" by the user who is logged

	static List<App> apps = new ArrayList<App>();

	static AppManager am = null;

	public static AppManager getInstance() {
		if(am == null) {
			am = new AppManager();
			User u1 = new User("joaorsantos", "xpto", "joaorsantos@gamify.pt"); // To remove when add MongoDB
			User u2 = new User("rcosta", "12345", "rcosta@gamify.pt"); // To remove when add MongoDB
			App a1 = new App("app1", u1, "Gamify UI", "Marketing", "Lorem Ipsum"); // To remove when add MongoDB
			App a2 = new App("app1", u2, "randomp", "Entertainment", "Lorem Ipsum 2"); // To remove when add MongoDB
			apps.add(a1); // To remove when add MongoDB
			apps.add(a2); // To remove when add MongoDB
		}
		return am;
	}

	@Override
	public void createApp(String appID, User userID, String appName, String type, String description) {
		App a = new App(appID, userID, appName, type, description);
		apps.add(a);
	}

	@Override
	public List<App> getApps(String userRequested) {

		if (userRequested.equals(userLogged)) { 
			List<App> filteredApps = new ArrayList<App>();
			for(App app:apps) {
				if (app.getUserID() == userLogged) {
					filteredApps.add(app);
				}
			}
			return filteredApps;
		}
		else {
			// The user is not authorized to see apps from another user - TO DO: Send error	
		}
		return null;

	}

	@Override
	public App getApp(String userID, String appID) {
		for(App app:apps) {
			if(app.getAppID().equals(appID)) {
				if(app.getUserID().equals(userLogged)) {
					return app;
				}
				else {
					// The user is not authorized to see that app - TO DO: Send error
				}
			}
			else {
				// The app not exists - TO DO: Send error
			}
		}
		return null;
	}

	@Override
	public void changeApp(String appID, App oldApp, App newApp) {
		// Change app
	}

	@Override
	public void removeApp(String appID) {
		for(App app:apps) {
			if (app.getAppID().equals(appID)) {
				if (app.getUserID().equals(userLogged)) { 
					apps.remove(app);
				}
				else {
					// The user is not authorized to remove - TO DO: Send error
				}
			}
			else {
				// The app not exists - TO DO: Send error
			}
		}
	}


}

