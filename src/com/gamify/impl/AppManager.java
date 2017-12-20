package com.gamify.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.gamify.interf.InterfaceApp;
import com.gamify.model.App;
import com.gamify.model.User;

public class AppManager implements InterfaceApp {
	
	static List<App> apps = new ArrayList<App>();
	
	static AppManager am = null;

	public static AppManager getInstance() {
		if(am == null) {
			am = new AppManager();
		}
		return am;
	}

	@Override
	public void createApp(String idApp, User username, String appName, String type, String description) {
		App a = new App(idApp, username, appName, type, description);
		apps.add(a);
	}

	@Override
	public List<App> getApps() {
		return apps;
	}

	@Override
	public App getApp(String idApp) {
		for (Iterator<App> iterator = apps.iterator(); iterator.hasNext();) {
			App a = (App) iterator.next();
			if(a.getIdApp().equals(idApp))
				return a;
		}
		return null;
	}

	@Override
	public void changeApp(String idApp, App oldApp, App newApp) {
//		for (Iterator<User> iterator = users.iterator(); iterator.hasNext();) {
//			User user = (User) iterator.next();
//			if(user.getUsername().equals(username))
//				//change here
//		}
	}

	@Override
	public void removeApp(String idApp) {
		for (Iterator<App> iterator = apps.iterator(); iterator.hasNext();) {
			App a = (App) iterator.next();
			if(a.getIdApp().equals(idApp))
				iterator.remove();
		}
	}
	
}

